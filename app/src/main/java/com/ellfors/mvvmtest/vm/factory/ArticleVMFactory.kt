package com.ellfors.mvvmtest.vm.factory

import com.ellfors.mvvmtest.base.BaseVM
import com.ellfors.mvvmtest.base.BaseVMFactory
import com.ellfors.mvvmtest.vm.repository.ArticleRepo
import com.ellfors.mvvmtest.vm.viewmodel.ArticleVM

/**
 * ArticleVMFactory
 * 2020-05-19 14:54
 */
class ArticleVMFactory constructor(private val mRepository: ArticleRepo) :
    BaseVMFactory() {

    override val mViewModel: BaseVM
        get() = ArticleVM(mRepository)
}