package org.fuckrkn1.android.tunnel

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.fuckrkn1.android.room.RoomManager
import org.fuckrkn1.android.room.entity.CountryDB

object CountryManager {

    private var countryList: List<CountryDB> = listOf()
    var activeCountry: CountryDB? = null

    suspend fun downloadListFromServer(): List<CountryDB> = withContext(Dispatchers.IO) {

        val newCountries = ApiManager.api.getLocations().map { CountryDB(code = it.code, name = it.name) }

        if (newCountries.isNotEmpty()) {
            RoomManager.insert(newCountries)
        } else {
            Log.d("CountryManager", "Empty countries list from server")
        }

        countryList = RoomManager.getAllCountries()

        return@withContext countryList
    }

    suspend fun getCountriesList(): List<CountryDB> = withContext(Dispatchers.IO) {
        return@withContext RoomManager.getAllCountries()
    }

    suspend fun restoreActiveCountry(): CountryDB? {

        val lastConfig = RoomManager.getSavedConfigForWG()

        activeCountry = countryList.firstOrNull { country -> country.code == lastConfig?.id }

        Log.d("CountryManager", "$activeCountry")

        return activeCountry
    }

    fun setupActiveCountryBy(name: String) {
        activeCountry = countryList.firstOrNull { it.name == name} ?: CountryDB("none", "None")
    }
}