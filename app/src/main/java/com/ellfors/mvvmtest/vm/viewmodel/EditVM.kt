package com.ellfors.mvvmtest.vm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ellfors.mvvmtest.base.BaseVM
import com.ellfors.mvvmtest.base.BaseVMFactory

/**
 * EditVM
 * 2020-05-25 12:05
 */
class EditVM : BaseVM() {

    class EditVMFactory : BaseVMFactory() {
        override val mViewModel: BaseVM
            get() = EditVM()
    }

    val userName = MutableLiveData("")
    val passWord = MutableLiveData("")
    val text = MutableLiveData("")

    fun log() {
        text.value = "账号：${userName.value}\n密码：${passWord.value}"
    }

}