package org.fuckrkn1.android.tunnel

import org.fuckrkn1.android.api.LibertyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    val api = Retrofit.Builder()
        .baseUrl("https://api.fuckrkn1.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LibertyApi::class.java)
}