package com.moengage.newsapp.navigation

sealed class Screen(
    val route: String,
) {
    data object Splash : Screen("splash_screen")
    data object MainScreen : Screen("main_screen")
    data object PWAScreen : Screen("pwa_screen")
    data object FavScreen : Screen("favorite_screen")

    fun withArgs(vararg args: String): String = buildString {
        append(route)
        args.forEach { args ->
            append("/$args")
        }
    }
}
