package org.fuckrkn1.android.ui.action

sealed class CommonUiEvent {
    object Back : CommonUiEvent()
    data class OpenUrl(val url: String) : CommonUiEvent()
    data class CopyToClipboard(val text: String) : CommonUiEvent()
}
