package com.ellfors.mvvmtest.vm.factory

import com.ellfors.mvvmtest.base.BaseViewModel
import com.ellfors.mvvmtest.base.BaseViewModelFactory
import com.ellfors.mvvmtest.vm.repository.ArticleRepository
import com.ellfors.mvvmtest.vm.viewmodel.ArticleVM

/**
 * ArticleVMFactory
 * 2020-05-19 14:54
 */
class ArticleVMFactory constructor(private val mRepository: ArticleRepository) :
    BaseViewModelFactory() {

    override val mViewModel: BaseViewModel
        get() = ArticleVM(mRepository)
}