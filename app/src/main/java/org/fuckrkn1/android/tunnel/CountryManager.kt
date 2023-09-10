package org.fuckrkn1.android.tunnel

import android.util.Log
import org.fuckrkn1.android.room.RoomManager
import org.fuckrkn1.android.room.entity.CountryDB

object CountryManager {

    suspend fun downloadListFromServer() {

        val locations = ApiManager.api.getLocations()

        if (locations.isNotEmpty()) {
            val countries = locations.map { CountryDB(code = it.code, name = it.name) }
            RoomManager.insert(countries)
        } else {
            Log.d("CountryManager", "Empty countries list from server")
        }
    }

    suspend fun getCountriesList(): List<CountryDB> {
        return RoomManager.getAllCountries()
    }
}