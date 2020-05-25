package com.ellfors.mvvmtest.vm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ellfors.mvvmtest.base.BaseViewModel

/**
 * EditVM
 * 2020-05-25 12:05
 */
class EditVM : BaseViewModel() {

    val userName = MutableLiveData("")
    val passWord = MutableLiveData("")
    val text = MutableLiveData("")

    fun log() {
        text.value = "账号：${userName.value}\n密码：${passWord.value}"
    }

}