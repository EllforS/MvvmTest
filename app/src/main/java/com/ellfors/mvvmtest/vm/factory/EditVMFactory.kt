package com.ellfors.mvvmtest.vm.factory

import com.ellfors.mvvmtest.base.BaseVM
import com.ellfors.mvvmtest.base.BaseVMFactory
import com.ellfors.mvvmtest.vm.viewmodel.EditVM

/**
 * EditVMFactory
 * 2020-05-25 12:07
 */
class EditVMFactory : BaseVMFactory() {
    override val mViewModel: BaseVM
        get() = EditVM()
}