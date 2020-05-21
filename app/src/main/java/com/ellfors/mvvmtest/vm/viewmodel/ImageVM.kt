package com.ellfors.mvvmtest.vm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ellfors.mvvmtest.base.BaseViewModel
import com.ellfors.mvvmtest.bean.Girl
import com.ellfors.mvvmtest.vm.repository.ImageRepository

/**
 * MainVM
 * 2020-05-06 12:26
 */
class ImageVM(private val mRepository: ImageRepository) : BaseViewModel() {

    var errorUrl =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1589888006423&di=f0647e1131ee22f9613b460ab1ca6a34&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F01%2F54%2F12%2F0157470154cc083.jpg"

    var girl = MutableLiveData<Girl>()

    fun refreshImage() {

        launchIO({
            mRepository.refreshImage()
        }, {
            girl.value = (it[0])
        }, {
            girl.value = Girl("", errorUrl)
        })
    }
}