package com.ellfors.mvvmtest.vm

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.ellfors.mvvmtest.vm.repository.ArticleRepo
import com.ellfors.mvvmtest.vm.repository.ImageRepo
import com.ellfors.mvvmtest.vm.viewmodel.*

/**
 * InjectUtils
 * 2020-05-06 15:40
 */
object VMInjectUtil {

    fun injectImageVM(owner: ViewModelStoreOwner): ImageVM {
        return ViewModelProvider(owner, ImageVM.ImageVMFactory(ImageRepo.instance)).get(ImageVM::class.java)
    }

    fun injectArticleVM(owner: ViewModelStoreOwner): ArticleVM {
        return ViewModelProvider(owner, ArticleVM.ArticleVMFactory(ArticleRepo.instance)).get(ArticleVM::class.java)
    }

    fun injectTimeDownVM(owner: ViewModelStoreOwner): TimeDownVM {
        return ViewModelProvider(owner, TimeDownVM.TimeDownVMFactory()).get(TimeDownVM::class.java)
    }

    fun injectEditVM(owner: ViewModelStoreOwner): EditVM {
        return ViewModelProvider(owner, EditVM.EditVMFactory()).get(EditVM::class.java)
    }

    fun injectMMKVVM(owner: ViewModelStoreOwner): MMKVVM {
        return ViewModelProvider(owner, MMKVVM.MMKVVMFactory()).get(MMKVVM::class.java)
    }
}