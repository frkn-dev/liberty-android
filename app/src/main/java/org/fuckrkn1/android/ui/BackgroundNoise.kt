package org.fuckrkn1.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import org.fuckrkn1.android.R

@Composable
@Preview
fun BackgroundNoise() {
    Image(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        painter = painterResource(id = R.drawable.bg_black_noise),
        contentScale = ContentScale.Crop,
        contentDescription = "background"
    )
}