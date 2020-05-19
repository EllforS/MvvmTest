package com.ellfors.mvvmtest.vm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ellfors.mvvmtest.base.BaseViewModel
import com.ellfors.mvvmtest.bean.Girl
import com.ellfors.mvvmtest.vm.repository.ImageRepository

/**
 * MainVM
 * 2020-05-06 12:26
 */
class ImageVM constructor(private val mRepository: ImageRepository) : BaseViewModel() {

    val mImage = MutableLiveData(Girl())

    fun refreshImage() {
        launchIO({
            mRepository.refreshImage()
        }, {
            mImage.postValue(it[0])
        })
    }

}