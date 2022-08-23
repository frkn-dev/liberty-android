package org.fuckrkn1.android.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import org.fuckrkn1.android.R

object ClipboardUtils {
    fun copyText(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager? ?: return
        val clip = ClipData.newPlainText("plain text", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, R.string.clipboard_copy_confirmation, Toast.LENGTH_SHORT).show()
    }
}