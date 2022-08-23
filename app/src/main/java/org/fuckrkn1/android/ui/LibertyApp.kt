package org.fuckrkn1.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.fuckrkn1.android.data.SocialNetwork
import org.fuckrkn1.android.navigation.AppRoute
import org.fuckrkn1.android.util.UrlUtils

@Composable
fun LibertyApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = AppRoute.MAIN) {
        composable(AppRoute.MAIN) {
            MainScreen { event ->
                when (event) {
                    MainUiEvent.ABOUT_CLICK -> navController.navigate(AppRoute.ABOUT)
                    MainUiEvent.SUPPORT_US_CLICK -> TODO()
                }
            }
        }
        composable(AppRoute.ABOUT) {
            AboutScreen(
                socialNetworks = SocialNetwork.ALL,
                onBackClick = { navController.popBackStack() },
                onUrlClick = { url -> UrlUtils.openUrl(context, url) }
            )
        }
    }
}