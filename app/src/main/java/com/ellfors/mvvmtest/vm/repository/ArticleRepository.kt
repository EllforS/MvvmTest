package com.ellfors.mvvmtest.vm.repository

import com.ellfors.mvvmtest.base.BaseRepository

/**
 * ArticleRepository
 * 2020-05-19 14:53
 */
class ArticleRepository : BaseRepository() {

    companion object {
        @Volatile
        private var instance: ArticleRepository? = null

        fun getInstance(): ArticleRepository =
            instance ?: synchronized(this) {
                instance ?: ArticleRepository().also {
                    instance = it
                }
            }
    }

    suspend fun getArticles() = getHttpService.getArticles()

}