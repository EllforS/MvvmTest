package com.ellfors.mvvmtest.vm.repository

import com.ellfors.mvvmtest.base.BaseRepo

/**
 * ArticleRepository
 * 2020-05-19 14:53
 */
class ArticleRepo : BaseRepo() {

    companion object {
        val instance: ArticleRepo by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ArticleRepo()
        }
    }

    suspend fun getArticles(page: Int) = getHttpService.getArticles(page)

}