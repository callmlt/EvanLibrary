package com.evan.lib.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

/**
 * ViewModel
 *
 * @author gr 2020-03-31
 */
class AppViewModel {
    fun init(application: Application) {
        val viewModelStore = ViewModelStore()
        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModelProvider = ViewModelProvider(viewModelStore, factory)
    }

    companion object {

        private var viewModelProvider: ViewModelProvider? = null

        fun <T : ViewModel> getViewModel(viewModelClass: Class<T>): T {
            if (!IViewModel::class.java.isAssignableFrom(viewModelClass)) {
                throw IllegalArgumentException("the model class should be subclass of IViewModel")
            }
            return viewModelProvider!!.get(viewModelClass)
        }
    }

}

