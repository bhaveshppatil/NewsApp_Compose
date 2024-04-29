package com.moengage.newsapp.remote

import com.moengage.newsapp.model.NewsResponse
import com.moengage.newsapp.remote.helpers.Result
import com.moengage.newsapp.remote.helpers.SafeApiRequest
import com.moengage.newsapp.ui.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.kodein.di.DI
import org.kodein.di.instance

class NewsRepositoryImpl(override val di: DI) : NewsRepository {
    private val safeApiRequest: SafeApiRequest by instance()
    private val newsApiService: NewsApiService by instance()
    override fun getListOfNews(): Flow<Result<NewsResponse>> = flow {
        emit(Result.Loading)
        emit(safeApiRequest.apiRequest {
            newsApiService.getListOfNews()
        })
    }
}