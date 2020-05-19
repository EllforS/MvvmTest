package com.ellfors.mvvmtest.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ellfors.mvvmtest.vm.repository.ImageRepository
import com.ellfors.mvvmtest.vm.viewmodel.ImageVM

/**
 * MainVMFactory
 * 2020-05-06 12:27
 */
@Suppress("UNCHECKED_CAST")
class ImageVMFactory constructor(private val mMainRepository: ImageRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageVM(mMainRepository) as T
    }
}