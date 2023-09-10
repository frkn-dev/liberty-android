package org.fuckrkn1.android.viewModels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.fuckrkn1.android.viewModels.CountrySpinnerViewModel

class CountrySpinnerViewModelFactory(): ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(CountrySpinnerViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CountrySpinnerViewModel() as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}