package com.moengage.newsapp.remote

import com.moengage.newsapp.model.NewsResponse
import com.moengage.newsapp.ui.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val apiService: NewsApiService,
    private val safeApiRequest: SafeApiRequest
) : NewsRepository {

    override fun getListOfNews(): Flow<Result<NewsResponse>> = flow {
        emit(Result.Loading)
        emit(safeApiRequest.apiRequest {
            apiService.getListOfNews()
        })
    }
}