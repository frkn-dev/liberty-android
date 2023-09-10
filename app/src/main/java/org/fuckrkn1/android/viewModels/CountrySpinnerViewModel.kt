package org.fuckrkn1.android.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.fuckrkn1.android.room.entity.CountryDB
import org.fuckrkn1.android.tunnel.CountryManager

class CountrySpinnerViewModel: ViewModel() {

    // MARK: - Properties

    private val _countries: MutableStateFlow<List<CountryDB>> = MutableStateFlow(listOf())
    val countries: StateFlow<List<CountryDB>>
        get() = _countries


    private val _selectedCountry: MutableStateFlow<CountryDB> = MutableStateFlow(CountryDB("none", "None"))
    val selectedCountry: StateFlow<CountryDB>
        get() = _selectedCountry

    // MARK: - Fetch
    suspend fun fetchCountries() {

        viewModelScope.run {

            _countries.value = CountryManager.getCountriesList()

            val newCountries = CountryManager.downloadListFromServer()
            if (newCountries.isNotEmpty()) {
                _countries.value = newCountries
            }

            _selectedCountry.value = CountryManager.restoreActiveCountry() ?: CountryDB("none", "None")
        }
    }

    fun setupActiveCountryBy(name: String) {

        CountryManager.setupActiveCountryBy(name)
        _selectedCountry.value = CountryManager.activeCountry ?: CountryDB("none", "None")
    }
}