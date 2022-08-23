package org.fuckrkn1.android.data

import androidx.annotation.DrawableRes
import org.fuckrkn1.android.R

data class SocialNetwork(
    val name: String,
    @DrawableRes val iconId: Int,
    val url: String,
) {
    companion object {
        val ALL = listOf(
            SocialNetwork(
                name = "Telegram",
                iconId = R.drawable.telegram_logo,
                url = "https://t.me/nezavisimost_dev"
            ),
            SocialNetwork(
                name = "Twitter",
                iconId = R.drawable.twitter_logo,
                url = "https://twitter.com/FuckRKN1"
            ),
            SocialNetwork(
                name = "GitHub",
                iconId = R.drawable.github_logo,
                url = "https://github.com/nezavisimost"
            )
        )
    }
}
