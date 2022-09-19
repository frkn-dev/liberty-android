package org.fuckrkn1.android.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import org.fuckrkn1.android.ui.drawable.MainDrawable
import org.fuckrkn1.android.ui.drawable.ScaleToFitWidthDrawable

@Composable
fun MainToggle(
    state: MainToggleState,
    onClickListener: () -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val drawable = remember {
            MainDrawable(context)
        }
        val scaleDrawable = remember {
            ScaleToFitWidthDrawable(drawable)
        }
        drawable.setState(state)
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        if (drawable.isInsideToggle(offset.x / scaleDrawable.scale, offset.y / scaleDrawable.scale)) {
                            onClickListener()
                        }
                    }
                },
            painter = rememberDrawablePainter(scaleDrawable),
            contentDescription = "Fire",
            contentScale = ContentScale.FillWidth,
        )
    }
}

@Composable
@Preview
fun MainToggleInactivePreview() {
    MainToggle(state = MainToggleState.INACTIVE, onClickListener = {})
}


@Composable
@Preview
fun MainToggleInactiveConnecting() {
    MainToggle(state = MainToggleState.IN_PROGRESS, onClickListener = {})
}

@Composable
@Preview
fun MainToggleActivePreview() {
    MainToggle(state = MainToggleState.ACTIVE, onClickListener = {})
}

enum class MainToggleState {
    ACTIVE,
    INACTIVE,
    IN_PROGRESS
}