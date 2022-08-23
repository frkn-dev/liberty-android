package org.fuckrkn1.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.fuckrkn1.android.R
import org.fuckrkn1.android.ui.style.TextStyles

@Composable
fun BackButton(
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.ic_back), contentDescription = "Back")
            Text(
                text = stringResource(id = R.string.arrow_back),
                style = TextStyles.textSemiBold(14.sp),
            )
        }
    }
}

@Composable
@Preview
private fun BackButtonPreview() {
    BackButton {}
}