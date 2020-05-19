package com.ellfors.mvvmtest.vm.repository

import com.ellfors.mvvmtest.base.BaseRepository

/**
 * MainRepository
 * 2020-05-06 15:42
 */
class ImageRepository : BaseRepository() {

    companion object {
        @Volatile
        private var instance: ImageRepository? = null

        fun getInstance(): ImageRepository =
            instance ?: synchronized(this) {
                instance ?: ImageRepository().also {
                    instance = it
                }
            }
    }

    suspend fun refreshImage() = getHttpService.refreshImage()

}