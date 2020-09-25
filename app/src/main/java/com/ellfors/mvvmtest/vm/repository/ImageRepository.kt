package com.ellfors.mvvmtest.vm.repository

import com.ellfors.mvvmtest.base.BaseRepository

/**
 * MainRepository
 * 2020-05-06 15:42
 */
class ImageRepository : BaseRepository() {

    companion object {
        val instance: ImageRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ImageRepository()
        }
    }

    suspend fun refreshImage() = getHttpService.refreshImage()

}