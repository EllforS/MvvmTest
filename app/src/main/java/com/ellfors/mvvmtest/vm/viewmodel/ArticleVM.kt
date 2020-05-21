package com.ellfors.mvvmtest.vm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ellfors.mvvmtest.base.BaseViewModel
import com.ellfors.mvvmtest.bean.ArticlesBean
import com.ellfors.mvvmtest.vm.repository.ArticleRepository

/**
 * ArticleVM
 * 2020-05-19 14:53
 */
class ArticleVM constructor(private val mRepository: ArticleRepository) : BaseViewModel() {

    val mArticles = MutableLiveData<MutableList<ArticlesBean>>()
    var isRefreshing = MutableLiveData(true)
    var isLoading = MutableLiveData(true)
    var hasMore = MutableLiveData(true)
    var mPage = 1

    fun refreshArticles() {
        isRefreshing.value = true
        mPage = 1
        getArticles(mPage)
    }

    fun loadArticles() {
        isLoading.value = true
        mPage++
        getArticles(mPage)
    }

    fun getArticles(page: Int) {
        launchIOByBase({
            mRepository.getArticles(page)
        }, {
            mArticles.value = it.data
            hasMore.value = it.page < it.page_count
        }, {

        }, {
            isRefreshing.value = false
            isLoading.value = false
        })
    }

}