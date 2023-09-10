package org.fuckrkn1.android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.fuckrkn1.android.R
import org.fuckrkn1.android.ui.style.TextStyles
import org.fuckrkn1.android.viewModels.CountrySpinnerViewModel

@Composable
fun MainScreen(
    uiEventListener: (MainUiEvent) -> Unit,
    mainToggleState: MainToggleState,
    countrySpinnerViewModel: CountrySpinnerViewModel?,
) {
    BackgroundNoise()
    MainDecoration()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(onClick = { uiEventListener(MainUiEvent.ABOUT_CLICK) }) {
            Text(
                text = stringResource(id = R.string.about),
                style = TextStyles.textSemiBold(14.sp)
            )
        }
        TextButton(onClick = { uiEventListener(MainUiEvent.SUPPORT_US_CLICK) }) {
            Text(
                text = stringResource(id = R.string.support_us),
                style = TextStyles.textSemiBold(14.sp)
            )
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        MainToggle(
            state = mainToggleState,
            onClickListener = { uiEventListener(MainUiEvent.TOGGLE_CLICK) }
        )
    }
    Box(
        modifier = Modifier.fillMaxSize().padding(vertical = 120.dp, horizontal = 30.dp),
        contentAlignment = Alignment.BottomEnd,
    ) {
        if (countrySpinnerViewModel != null) { CountrySpinner(countrySpinnerViewModel) }
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen(uiEventListener = {}, mainToggleState = MainToggleState.INACTIVE, null)
}

enum class MainUiEvent {
    ABOUT_CLICK,
    SUPPORT_US_CLICK,
    TOGGLE_CLICK
}