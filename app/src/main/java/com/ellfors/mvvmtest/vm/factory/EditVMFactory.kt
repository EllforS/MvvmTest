package com.ellfors.mvvmtest.vm.factory

import com.ellfors.mvvmtest.base.BaseViewModel
import com.ellfors.mvvmtest.base.BaseViewModelFactory
import com.ellfors.mvvmtest.vm.viewmodel.EditVM

/**
 * EditVMFactory
 * 2020-05-25 12:07
 */
class EditVMFactory : BaseViewModelFactory() {
    override val mViewModel: BaseViewModel
        get() = EditVM()
}