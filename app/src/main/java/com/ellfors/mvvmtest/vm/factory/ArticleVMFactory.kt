package com.ellfors.mvvmtest.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ellfors.mvvmtest.vm.repository.ArticleRepository
import com.ellfors.mvvmtest.vm.viewmodel.ArticleVM

/**
 * ArticleVMFactory
 * 2020-05-19 14:54
 */
class ArticleVMFactory constructor(private val mRepository: ArticleRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleVM(mRepository) as T
    }
}