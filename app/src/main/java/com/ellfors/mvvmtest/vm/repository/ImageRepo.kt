package com.ellfors.mvvmtest.vm.repository

import com.ellfors.mvvmtest.base.BaseRepo

/**
 * MainRepository
 * 2020-05-06 15:42
 */
class ImageRepo : BaseRepo() {

    companion object {
        val instance: ImageRepo by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ImageRepo()
        }
    }

    suspend fun refreshImage() = getHttpService.refreshImage()

}