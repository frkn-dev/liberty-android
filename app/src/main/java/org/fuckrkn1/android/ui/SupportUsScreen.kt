package org.fuckrkn1.android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.fuckrkn1.android.R
import org.fuckrkn1.android.data.SupportRequisite
import org.fuckrkn1.android.data.SupportService
import org.fuckrkn1.android.ui.action.CommonUiEvent
import org.fuckrkn1.android.ui.style.TextStyles
import java.text.AttributedString

@Composable
fun SupportUsScreen(
    requisites: List<SupportRequisite>,
    services: List<SupportService>,
    uiEventListener: (CommonUiEvent) -> Unit,
) {
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
                text = stringResource(id = R.string.support_us_title),
                style = TextStyles.textSemiBold(22.sp),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.support_us_description),
                style = TextStyles.textNormal(14.sp),
            )

            requisites.forEach { requisite ->
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = requisite.name,
                    style = TextStyles.textSemiBold(14.sp)
                )
                ClickableText(
                    modifier = Modifier.padding(top = 4.dp),
                    text = AnnotatedString(requisite.account),
                    style = TextStyles.textNormal(12.sp),
                    onClick = { uiEventListener(CommonUiEvent.CopyToClipboard(requisite.account)) }
                )
            }

            Row(
                modifier = Modifier.padding(vertical = 28.dp),
                horizontalArrangement = Arrangement.spacedBy(48.dp)
            ) {
                services.forEach { service ->
                    ClickableText(
                        text = AnnotatedString(service.name),
                        style = TextStyles.textSemiBold(14.sp),
                        onClick = { uiEventListener(CommonUiEvent.OpenUrl(service.url)) }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun SupportUsScreenPreview() {
    SupportUsScreen(
        requisites = SupportRequisite.ALL,
        services = SupportService.ALL,
        uiEventListener = {},
    )
}