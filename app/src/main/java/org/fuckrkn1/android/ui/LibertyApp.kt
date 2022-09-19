package org.fuckrkn1.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.fuckrkn1.android.data.SocialNetwork
import org.fuckrkn1.android.data.SupportRequisite
import org.fuckrkn1.android.data.SupportService
import org.fuckrkn1.android.navigation.AppRoute
import org.fuckrkn1.android.ui.action.CommonUiEvent
import org.fuckrkn1.android.util.ClipboardUtils
import org.fuckrkn1.android.util.UrlUtils

@Composable
fun LibertyApp(
    mainToggleState: MainToggleState,
    onToggleClick: () -> Unit,
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val commonUiEventListener = { event: CommonUiEvent ->
        when (event) {
            CommonUiEvent.Back -> navController.popBackStack()
            is CommonUiEvent.CopyToClipboard -> ClipboardUtils.copyText(context, event.text)
            is CommonUiEvent.OpenUrl -> UrlUtils.openUrl(context, event.url)
        }
        Unit
    }

    NavHost(navController = navController, startDestination = AppRoute.MAIN) {
        composable(AppRoute.MAIN) {
            MainScreen(
                uiEventListener = { event ->
                    when (event) {
                        MainUiEvent.ABOUT_CLICK -> navController.navigate(AppRoute.ABOUT)
                        MainUiEvent.SUPPORT_US_CLICK -> navController.navigate(AppRoute.SUPPORT_US)
                        MainUiEvent.TOGGLE_CLICK -> onToggleClick()
                    }
                },
                mainToggleState = mainToggleState,
            )
        }
        composable(AppRoute.ABOUT) {
            AboutScreen(
                socialNetworks = SocialNetwork.ALL,
                uiEventListener = commonUiEventListener,
            )
        }
        composable(AppRoute.SUPPORT_US) {
            SupportUsScreen(
                requisites = SupportRequisite.ALL,
                services = SupportService.ALL,
                uiEventListener = commonUiEventListener,
            )
        }
    }
}