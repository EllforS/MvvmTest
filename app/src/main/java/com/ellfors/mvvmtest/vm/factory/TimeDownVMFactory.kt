package com.ellfors.mvvmtest.vm.factory

import com.ellfors.mvvmtest.base.BaseViewModel
import com.ellfors.mvvmtest.base.BaseViewModelFactory
import com.ellfors.mvvmtest.vm.viewmodel.TimeDownVM

/**
 * TimeDownVMFactory
 * 2020-05-19 16:59
 */
class TimeDownVMFactory : BaseViewModelFactory() {

    override val mViewModel: BaseViewModel
        get() = TimeDownVM()
}