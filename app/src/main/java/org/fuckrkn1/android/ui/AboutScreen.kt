package org.fuckrkn1.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.fuckrkn1.android.R
import org.fuckrkn1.android.data.SocialNetwork
import org.fuckrkn1.android.ui.action.CommonUiEvent
import org.fuckrkn1.android.ui.style.TextStyles

@Composable
fun AboutScreen(
    socialNetworks: List<SocialNetwork>,
    uiEventListener: (CommonUiEvent) -> Unit,
) {
    BackgroundNoise()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 16.dp)
        ) {
            TextButton(onClick = { uiEventListener(CommonUiEvent.Back) }) {
                Text(
                    text = stringResource(id = R.string.arrow_back),
                    style = TextStyles.textSemiBold(14.sp),
                )
            }
            Text(
                text = stringResource(id = R.string.about_title),
                style = TextStyles.textSemiBold(22.sp),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.about_top_description),
                style = TextStyles.textNormal(14.sp),
            )
        }
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.about_image),
            contentDescription = "About image"
        )
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.about_bottom_description),
                style = TextStyles.textNormal(14.sp),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            socialNetworks.forEach {
                TextButton(
                    onClick = { uiEventListener(CommonUiEvent.OpenUrl(it.url)) },
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(id = it.iconId),
                            contentDescription = it.name,
                            contentScale = ContentScale.Inside,
                        )
                        Text(
                            modifier = Modifier.padding(top = 4.dp),
                            text = it.name,
                            style = TextStyles.textNormal(12.sp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun AboutScreenPreview() {
    AboutScreen(socialNetworks = SocialNetwork.ALL, uiEventListener = {})
}