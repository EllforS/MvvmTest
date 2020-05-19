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

    val mArticles = MutableLiveData<List<ArticlesBean>>()

    fun getArticles() {
        launchIO({
            mRepository.getArticles()
        }, {
            mArticles.postValue(it)
        })
    }

}