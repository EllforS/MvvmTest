package com.ellfors.mvvmtest.vm.factory

import com.ellfors.mvvmtest.base.BaseViewModel
import com.ellfors.mvvmtest.base.BaseViewModelFactory
import com.ellfors.mvvmtest.vm.repository.ImageRepository
import com.ellfors.mvvmtest.vm.viewmodel.ImageVM

/**
 * MainVMFactory
 * 2020-05-06 12:27
 */
@Suppress("UNCHECKED_CAST")
class ImageVMFactory constructor(private val mMainRepository: ImageRepository) :
    BaseViewModelFactory() {

    override val mViewModel: BaseViewModel
        get() = ImageVM(mMainRepository)
}