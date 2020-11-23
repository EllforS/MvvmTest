package com.ellfors.mvvmtest.vm.factory

import com.ellfors.mvvmtest.base.BaseVM
import com.ellfors.mvvmtest.base.BaseVMFactory
import com.ellfors.mvvmtest.vm.repository.ImageRepo
import com.ellfors.mvvmtest.vm.viewmodel.ImageVM

/**
 * MainVMFactory
 * 2020-05-06 12:27
 */
@Suppress("UNCHECKED_CAST")
class ImageVMFactory constructor(private val mMainRepository: ImageRepo) :
    BaseVMFactory() {

    override val mViewModel: BaseVM
        get() = ImageVM(mMainRepository)
}