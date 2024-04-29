package com.moengage.newsapp.ui.repository

import com.moengage.newsapp.model.NewsResponse
import com.moengage.newsapp.remote.helpers.Result
import kotlinx.coroutines.flow.Flow
import org.kodein.di.DIAware

interface NewsRepository: DIAware {

    fun getListOfNews(): Flow<Result<NewsResponse>>

}