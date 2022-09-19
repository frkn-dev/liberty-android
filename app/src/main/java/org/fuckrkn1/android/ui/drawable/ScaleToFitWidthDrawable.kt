package org.fuckrkn1.android.ui.drawable

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.drawable.Drawable
import androidx.core.graphics.withScale

class ScaleToFitWidthDrawable(val drawable: Drawable) : Drawable(), Drawable.Callback {

    var scale: Float = 0f

    init {
        drawable.callback =  this
    }

    override fun getIntrinsicWidth(): Int {
        return drawable.intrinsicWidth
    }

    override fun getIntrinsicHeight(): Int {
        return drawable.intrinsicHeight
    }

    override fun draw(canvas: Canvas) {
        scale = drawable.bounds.width().toFloat() / drawable.intrinsicWidth
        canvas.withScale(x = scale, y = scale) {
            drawable.draw(canvas)
        }
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        drawable.setBounds(left, top, right, bottom)
    }

    override fun setAlpha(alpha: Int) {
        drawable.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        drawable.colorFilter = colorFilter
    }

    override fun getOpacity(): Int = drawable.opacity

    override fun invalidateDrawable(who: Drawable) {
        callback?.invalidateDrawable(who)
    }

    override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
        callback?.scheduleDrawable(who, what, `when`)
    }

    override fun unscheduleDrawable(who: Drawable, what: Runnable) {
        callback?.unscheduleDrawable(who, what)
    }

}