package org.fuckrkn1.android.util

import android.content.Context
import android.content.Intent
import android.net.Uri

object UrlUtils {
    fun openUrl(context: Context, url: String) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}