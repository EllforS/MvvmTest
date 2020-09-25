package com.ellfors.mvvmtest.vm.repository

import com.ellfors.mvvmtest.base.BaseRepository

/**
 * ArticleRepository
 * 2020-05-19 14:53
 */
class ArticleRepository : BaseRepository() {

    companion object {
        val instance: ArticleRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ArticleRepository()
        }
    }

    suspend fun getArticles(page: Int) = getHttpService.getArticles(page)

}