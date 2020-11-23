package com.ellfors.mvvmtest.vm.factory

import com.ellfors.mvvmtest.base.BaseVM
import com.ellfors.mvvmtest.base.BaseVMFactory
import com.ellfors.mvvmtest.vm.viewmodel.TimeDownVM

/**
 * TimeDownVMFactory
 * 2020-05-19 16:59
 */
class TimeDownVMFactory : BaseVMFactory() {

    override val mViewModel: BaseVM
        get() = TimeDownVM()
}