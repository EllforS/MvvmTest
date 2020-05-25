package com.ellfors.mvvmtest.vm

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.ellfors.mvvmtest.vm.repository.ArticleRepository
import com.ellfors.mvvmtest.vm.repository.ImageRepository
import com.ellfors.mvvmtest.vm.factory.ArticleVMFactory
import com.ellfors.mvvmtest.vm.factory.EditVMFactory
import com.ellfors.mvvmtest.vm.factory.ImageVMFactory
import com.ellfors.mvvmtest.vm.factory.TimeDownVMFactory
import com.ellfors.mvvmtest.vm.viewmodel.ArticleVM
import com.ellfors.mvvmtest.vm.viewmodel.EditVM
import com.ellfors.mvvmtest.vm.viewmodel.ImageVM
import com.ellfors.mvvmtest.vm.viewmodel.TimeDownVM

/**
 * InjectUtils
 * 2020-05-06 15:40
 */
object InjectUtils {

    fun injectImageVM(owner: ViewModelStoreOwner): ImageVM {
        return ViewModelProvider(owner, ImageVMFactory(ImageRepository.getInstance())).get(ImageVM::class.java)
    }

    fun injectArticleVM(owner: ViewModelStoreOwner): ArticleVM {
        return ViewModelProvider(owner, ArticleVMFactory(ArticleRepository.getInstance())).get(ArticleVM::class.java)
    }

    fun injectTimeDownVM(owner: ViewModelStoreOwner): TimeDownVM {
        return ViewModelProvider(owner, TimeDownVMFactory()).get(TimeDownVM::class.java)
    }

    fun injectEditVM(owner: ViewModelStoreOwner): EditVM {
        return ViewModelProvider(owner, EditVMFactory()).get(EditVM::class.java)
    }
}