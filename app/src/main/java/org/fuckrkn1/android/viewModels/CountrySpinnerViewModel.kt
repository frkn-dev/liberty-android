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

    // MARK: - Fetch
    suspend fun fetchCountries() {

        viewModelScope.run {
            val savedCountries = CountryManager.getCountriesList()
            if (savedCountries.isEmpty()) {
                _countries.value = CountryManager.downloadListFromServer()
            } else {
                _countries.value = savedCountries
            }
        }
    }
}