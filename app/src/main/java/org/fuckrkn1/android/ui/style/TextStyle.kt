package org.fuckrkn1.android.ui.style

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import org.fuckrkn1.android.R

object TextStyles {
    fun blackText(fontSize: TextUnit) = TextStyle(
        color = Color.Black,
        fontSize = fontSize,
        fontFamily = FontFamily(Font(R.font.exo2)),
    )
}