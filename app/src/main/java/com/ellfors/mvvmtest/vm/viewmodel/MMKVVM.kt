package com.ellfors.mvvmtest.vm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ellfors.mvvmtest.base.BaseVM
import com.ellfors.mvvmtest.base.BaseVMFactory
import com.ellfors.mvvmtest.utils.SpUtil
import com.tencent.mmkv.MMKV

/**
 * MMKVVM
 * 2020-11-30 10:35
 */
class MMKVVM : BaseVM() {

    class MMKVVMFactory : BaseVMFactory() {
        override val mViewModel: BaseVM
            get() = MMKVVM()
    }

    var mValue = MutableLiveData("")
    val key = "key"

    fun save() {
        SpUtil.getInstance().saveData(key, "123")
    }

    fun load() {
        mValue.value = SpUtil.getInstance().getString(key)
    }

    fun delete() {
        SpUtil.getInstance().removeKey(key)
    }

    fun clear() {
        SpUtil.getInstance().clearAll()
    }
}