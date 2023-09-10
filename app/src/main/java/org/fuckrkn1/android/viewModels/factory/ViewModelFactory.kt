package org.fuckrkn1.android.viewModels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory<T : ViewModel>(private val createViewModel: () -> T) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            return createViewModel.invoke() as T
        } catch (e: Exception) {
            throw IllegalArgumentException(
                "Failed to instantiate $modelClass with expected constructor(Application, AppDepsProvider)", e)
        }
    }
}