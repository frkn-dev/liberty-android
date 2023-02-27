package org.fuckrkn1.android.api

import com.google.gson.annotations.SerializedName

data class ApiLocation(
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String
)
