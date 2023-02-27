package org.fuckrkn1.android.api

import retrofit2.http.GET
import retrofit2.http.Query

interface LibertyApi {
    @GET("locations")
    suspend fun getLocations(): List<ApiLocation>

    @GET("peer")
    suspend fun getPeer(@Query("location") location: String): ApiLocationConfig
}