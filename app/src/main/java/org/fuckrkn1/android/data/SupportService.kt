package org.fuckrkn1.android.data

data class SupportService(
    val name: String,
    val url: String,
) {
    companion object {
        val ALL = listOf(
            SupportService(
                name = "Patreon",
                url = "https://www.patreon.com/"
            ),
            SupportService(
                name = "Boosty",
                url = "https://boosty.to/"
            ),
        )
    }
}
