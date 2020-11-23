package com.ellfors.mvvmtest.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * BaseViewModelFactory
 * 2020-05-25 12:05
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseVMFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return mViewModel as T
    }

    abstract val mViewModel: BaseVM
}