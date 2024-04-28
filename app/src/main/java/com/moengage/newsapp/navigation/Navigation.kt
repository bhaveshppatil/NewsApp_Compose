package com.moengage.newsapp.navigation

import NewsWebView
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moengage.newsapp.ui.presentation.composables.NewsScreen
import com.moengage.newsapp.ui.presentation.composables.SplashScreen
import com.moengage.newsapp.ui.viewmodel.NewsViewModel
import com.moengage.newsapp.utils.SortingOrder
import com.moengage.newsapp.utils.Status
import com.moengage.newsapp.utils.observeResourceAsState

@Composable
fun Navigation(
    viewModel: NewsViewModel,
) {
    val navController = rememberNavController()

    BackHandler {
        if (navController.currentBackStackEntry?.destination?.route == Screen.Splash.route) {
            // Handle back button press on the Splash screen if needed
        } else {
            navController.popBackStack()
        }
    }

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            SplashScreen(onLetsGoClicked = {
                viewModel.getListOfNewsData()
                navController.navigate(Screen.MainScreen.route) {
                    launchSingleTop = true
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(route = Screen.MainScreen.route) {
            val listOfNewsResource = viewModel.listOfNewsData.observeResourceAsState()
            val listOfNewsData = listOfNewsResource?.data
            val isLoading = listOfNewsResource?.status == Status.LOADING

            NewsScreen(
                navController = navController,
                listOfArticles = listOfNewsData,
                onOlderToNewerFilterApply = {
                    viewModel.filterArticlesByDate(
                        listOfNewsData,
                        sortingOrder = SortingOrder.DESCENDING
                    )
                },
                onArticleClicked = { articleUrl ->
                    viewModel.setArticleUrl(articleUrl)
                    navController.navigate(Screen.PWAScreen.route) {
                        launchSingleTop = true
                        popUpTo(Screen.MainScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = Screen.PWAScreen.route) {
            val articleUrl = viewModel.articleUrl.value

            if (!articleUrl.isNullOrBlank()) {
                NewsWebView(articleLink = articleUrl, onBackPressed = {
                    navController.navigate(Screen.MainScreen.route) {
                        popUpTo(Screen.MainScreen.route) {
                            inclusive = true
                        }
                    }
                })
            } else {
                Text("Article URL not provided")
            }
        }
    }
}