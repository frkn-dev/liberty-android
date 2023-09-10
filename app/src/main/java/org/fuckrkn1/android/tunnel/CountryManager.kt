package org.fuckrkn1.android.tunnel

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.fuckrkn1.android.room.RoomManager
import org.fuckrkn1.android.room.entity.CountryDB

object CountryManager {

    var activeCountry: CountryDB? = null

    suspend fun downloadListFromServer(): List<CountryDB> = withContext(Dispatchers.IO) {

        val countries = ApiManager.api.getLocations().map { CountryDB(code = it.code, name = it.name) }

        if (countries.isNotEmpty()) {
            RoomManager.insert(countries)
        } else {
            Log.d("CountryManager", "Empty countries list from server")
        }

        return@withContext countries
    }

    suspend fun getCountriesList(): List<CountryDB> = withContext(Dispatchers.IO) {
        return@withContext RoomManager.getAllCountries()
    }
}