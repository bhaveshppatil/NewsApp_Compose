package com.moengage.newsapp.ui.repository

import com.moengage.newsapp.model.NewsResponse
import com.moengage.newsapp.remote.Result
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getListOfNews(): Flow<Result<NewsResponse>>

}