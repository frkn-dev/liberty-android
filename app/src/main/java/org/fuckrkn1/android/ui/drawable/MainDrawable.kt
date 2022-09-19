package org.fuckrkn1.android.ui.drawable

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import androidx.compose.ui.geometry.Offset
import androidx.core.graphics.drawable.toBitmap
import org.fuckrkn1.android.R
import org.fuckrkn1.android.extension.dp
import org.fuckrkn1.android.extension.drawable
import org.fuckrkn1.android.ui.MainToggleState
import kotlin.math.max

class MainDrawable(private val context: Context) : Drawable() {

    private val leftFire = context.drawable(R.drawable.fire_left).toBitmap(config = Bitmap.Config.ARGB_8888)
    private val rightFire = context.drawable(R.drawable.fire_right).toBitmap(config = Bitmap.Config.ARGB_8888)
    private val pterodactyl = context.drawable(R.drawable.pterodactyl).toBitmap(config = Bitmap.Config.ARGB_8888)
    private val toggleInactive = context.drawable(R.drawable.main_toggle_inactive)
        .toBitmap(config = Bitmap.Config.ARGB_8888)
    private val toggleActive = context.drawable(R.drawable.main_toggle_active)
        .toBitmap(config = Bitmap.Config.ARGB_8888)

    private val width by lazy { calculateWidth() }
    private val height by lazy { calculateHeight() }
    private val clickableRectF by lazy { calculateClickableRectF() }

    private var state = MainToggleState.INACTIVE

    fun setState(state: MainToggleState) {
        this.state = state
        invalidateSelf()
    }

    fun isInsideToggle(x: Float, y: Float): Boolean = clickableRectF.contains(x, y)

    override fun getIntrinsicWidth(): Int {
        return width
    }

    override fun getIntrinsicHeight(): Int {
        return height
    }

    override fun draw(canvas: Canvas) {
        if (shouldDrawFire()) {
            canvas.drawBitmap(leftFire, 0f, -context.dp(4f), null)
            canvas.drawBitmap(rightFire, context.dp(238f), 0f, null)
        }
        if (isToggleActive()) {
            canvas.drawBitmap(toggleActive, context.dp(41f), context.dp(144f), null)
        } else {
            canvas.drawBitmap(toggleInactive, context.dp(114f), context.dp(216f), null)
        }
        canvas.drawBitmap(pterodactyl, context.dp(38f), context.dp(64f), null)
    }

    override fun setAlpha(alpha: Int) {
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }

    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

    private fun calculateWidth(): Int {
        return context.dp(238) + rightFire.width
    }

    private fun calculateHeight(): Int {
        return context.dp(144) + toggleActive.height - context.dp(32)
    }

    // Clickable area is the the same as area of inactive drawable. Active drawable has additional padding.
    private fun calculateClickableRectF(): RectF = RectF(
        context.dp(114f),
        context.dp(216f),
        context.dp(114f) + toggleInactive.width,
        context.dp(216f) + toggleInactive.height
    )

    private fun shouldDrawFire() = when (state) {
        MainToggleState.ACTIVE -> false
        MainToggleState.INACTIVE -> false
        MainToggleState.IN_PROGRESS -> true
    }

    private fun isToggleActive() = when (state) {
        MainToggleState.ACTIVE -> true
        MainToggleState.INACTIVE -> false
        MainToggleState.IN_PROGRESS -> false
    }
}