package com.moengage.newsapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object DateUtils {
    @SuppressLint("SimpleDateFormat")
    private val inputFormat = SimpleDateFormat(Constants.DATE_FORMAT)

    @SuppressLint("SimpleDateFormat")
    fun convertTimestampToDateString(timestamp: String, dateFormat: String): String {
        val date = inputFormat.parse(timestamp)
        val outputFormat = SimpleDateFormat(dateFormat)
        return outputFormat.format(date)
    }
}