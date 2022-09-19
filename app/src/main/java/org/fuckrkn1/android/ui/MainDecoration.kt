package org.fuckrkn1.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import org.fuckrkn1.android.R

@Composable
@Preview
fun MainDecoration() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.wire_top),
            contentScale = ContentScale.FillWidth,
            contentDescription = "Top decoration"
        )
        Image(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            painter = painterDrawable(R.drawable.main_bottom_decoration),
            contentScale = ContentScale.FillWidth,
            contentDescription = "Bottom decoration"
        )
    }
}
