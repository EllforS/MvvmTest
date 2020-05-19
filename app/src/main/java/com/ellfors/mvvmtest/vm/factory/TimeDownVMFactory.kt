package com.ellfors.mvvmtest.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.ellfors.mvvmtest.vm.viewmodel.TimeDownVM

/**
 * TimeDownVMFactory
 * 2020-05-19 16:59
 */
class TimeDownVMFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TimeDownVM() as T
    }
}