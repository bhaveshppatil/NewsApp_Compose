package com.moengage.newsapp.ui.viewmodel

import android.accounts.AccountManager.KEY_ERROR_MESSAGE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moengage.newsapp.model.NewsResponse
import com.moengage.newsapp.remote.NetworkMonitor
import com.moengage.newsapp.remote.NewsRepositoryImpl
import com.moengage.newsapp.remote.Result
import com.moengage.newsapp.utils.Constants.INTERNET_NOT_FOUND_STATUS_CODE
import com.moengage.newsapp.utils.Constants.NO_INTERNET_ERROR
import com.moengage.newsapp.utils.Resource
import kotlinx.coroutines.launch
import timber.log.Timber

class NewsViewModel(
    private val newsRepository: NewsRepositoryImpl,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _newsLiveData = MutableLiveData<Resource<NewsResponse>>()
    val listOfNewsData: LiveData<Resource<NewsResponse>>
        get() = _newsLiveData

    fun getListOfNewsData() {
        _newsLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            if (networkMonitor.isCurrentlyConnected()) {
                newsRepository.getListOfNews().collect {
                    when (it) {
                        is Result.Error -> {
                            val hashMap = HashMap<String, String>()
                            hashMap[KEY_ERROR_MESSAGE] = it.errorMessage
                            Timber.tag("NewsResponse Error").d(it.errorMessage)
                            _newsLiveData.postValue(
                                Resource.error(it.errorMessage, null, 0)
                            )
                        }

                        Result.Loading -> {
                            _newsLiveData.postValue(Resource.loading(null))
                        }

                        is Result.Success -> {
                            Timber.tag("NewsResponse Success").d(it.data.toString())
                            _newsLiveData.postValue(Resource.success(it.data))
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
}