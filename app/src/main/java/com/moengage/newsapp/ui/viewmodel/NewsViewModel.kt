package com.moengage.newsapp.ui.viewmodel

import android.accounts.AccountManager.KEY_ERROR_MESSAGE
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.moengage.newsapp.model.Article
import com.moengage.newsapp.remote.helpers.NetworkMonitor
import com.moengage.newsapp.remote.helpers.Result
import com.moengage.newsapp.ui.repository.NewsRepository
import com.moengage.newsapp.utils.Constants.INTERNET_NOT_FOUND_STATUS_CODE
import com.moengage.newsapp.utils.Constants.NO_INTERNET_ERROR
import com.moengage.newsapp.utils.Resource
import com.moengage.newsapp.utils.SortingOrder
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewsViewModel(app: Application) : AndroidViewModel(app), DIAware {
    override val di: DI by closestDI()

    private val networkHelper: NetworkMonitor by instance()
    private val newsRepository: NewsRepository by instance()

    private val _newsLiveData = MutableLiveData<Resource<List<Article>>>()
    val listOfNewsData: LiveData<Resource<List<Article>>>
        get() = _newsLiveData

    private val _articleUrl = MutableLiveData<String>()
    val articleUrl: LiveData<String>
        get() = _articleUrl

    fun getListOfNewsData() {
        _newsLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            if (networkHelper.isCurrentlyConnected()) {
                newsRepository.getListOfNews().collect {
                    when (it) {
                        is Result.Error -> {
                            val hashMap = HashMap<String, String>()
                            hashMap[KEY_ERROR_MESSAGE] = it.errorMessage
                            _newsLiveData.postValue(
                                Resource.error(it.errorMessage, null, 0)
                            )
                        }

                        Result.Loading -> {
                            _newsLiveData.postValue(Resource.loading(null))
                        }

                        is Result.Success -> {
                            it.data.articles.let { articleList ->
                                _newsLiveData.postValue(Resource.success(articleList))
                            }
                        }
                    }
                }
            } else {
                _newsLiveData.postValue(
                    Resource.error(
                        NO_INTERNET_ERROR,
                        null,
                        INTERNET_NOT_FOUND_STATUS_CODE
                    )
                )
            }
        }
    }

    fun filterArticlesByDate(
        articles: List<Article>?,
        sortingOrder: SortingOrder = SortingOrder.ASCENDING,
        targetDate: LocalDateTime = LocalDateTime.now()
    ) {
        articles
            ?.filter { article ->
                runCatching {
                    LocalDateTime.parse(article.publishedAt, DateTimeFormatter.ISO_DATE_TIME)
                }.getOrElse {
                    // If parsing fails, return null to filter out the article
                    return@filter false
                }?.let { articleDateTime ->

                    when (sortingOrder) {
                        SortingOrder.ASCENDING -> articleDateTime.isAfter(targetDate)
                        SortingOrder.DESCENDING -> articleDateTime.isBefore(targetDate)
                    }
                } ?: false
            }
            ?.let { filteredArticles ->
                val sortedFilteredArticles = if (sortingOrder == SortingOrder.ASCENDING) {
                    filteredArticles.sortedBy {
                        LocalDateTime.parse(it.publishedAt, DateTimeFormatter.ISO_DATE_TIME)
                    }
                } else {
                    filteredArticles.sortedByDescending {
                        LocalDateTime.parse(it.publishedAt, DateTimeFormatter.ISO_DATE_TIME)
                    }
                }

                _newsLiveData.postValue(Resource.success(sortedFilteredArticles))
            }
    }

    fun setArticleUrl(url: String) {
        _articleUrl.value = url
    }
}