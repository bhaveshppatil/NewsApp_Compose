package com.moengage.newsapp.ui.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.moengage.newsapp.navigation.Navigation
import com.moengage.newsapp.ui.theme.NewsAppTheme
import com.moengage.newsapp.ui.viewmodel.NewsViewModel
import org.kodein.di.compose.rememberInstance

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                val viewmodel: NewsViewModel by rememberInstance()
                Navigation(viewModel = viewmodel)
            }
        }
    }
}
