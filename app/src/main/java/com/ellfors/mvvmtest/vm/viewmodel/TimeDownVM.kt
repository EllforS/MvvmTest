package com.ellfors.mvvmtest.vm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ellfors.mvvmtest.base.BaseVM
import com.ellfors.mvvmtest.base.BaseVMFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

/**
 * TimeDownVM
 * 2020-05-19 16:59
 */
class TimeDownVM : BaseVM() {

    class TimeDownVMFactory : BaseVMFactory() {
        override val mViewModel: BaseVM
            get() = TimeDownVM()
    }

    val mTime = MutableLiveData("")
    val btnEnable = MutableLiveData(true)

    @ExperimentalCoroutinesApi
    fun startTimeDown() {

        /**
         * flow类似于RxJava
         * flow：构建器，他可以发射数据多个数据，用emit()来发射
         * flowOf：用于从一组给定的值创建流。
         *     flowOf(4, 2, 5, 1, 7).onEach { delay(400) }.flowOn(Dispatcher.Default)
         *
         * asFlow：这是一个扩展功能，有助于将类型转换为流。
         *     (1..5).asFlow().onEach{ delay(300) }.flowOn(Dispatchers.Default)
         *
         * channelFlow：此构建器使用构建器本身提供的send与元素创建冷流。
         *     channelFlow {
         *         (0..10).forEach {
         *             send(it)
         *         }
         *     }.flowOn(Dispatchers.Default)
         *
         * flatMapConcat ：这个是在一个流收集完成之后，再收集下一个流
         * onStart：这个看名字估计也能猜出来，就是在发射之前做一些事情，我们可以在这里再 emit()一个数据，他会在flow里边的数据发射之前发射，我们上边的例子，是在OnStart里边打开了等待框
         * flowOn：这个就是指定我们的流运行在那个协程里边，我们指定的是 Dispatchers.IO
         * onCompletion ：是在所有流都收集完成了，就会触发，我们可以在这里取消等待框再合适不过了
         * catch：这个就是遇到错误的时候会触发，我们我错误处理就是在这里来做了
         * collect：这个就是收集器的意思，我们的结果都在这里来处理。也只有我们调用了这个收集方法，数据才真正的开始发射了，这也是官方说的一句话，流是冷的，就是这个意思
         *
         * zip：合并流  注意：如果两个流没有相同的项目数，则其中一个流完成后，该流将立即停止。
         *     flowOne.zip(flowTwo) { strOne, strTwo ->
         *         "$strOne + $strTwo"
         *     }.collect {
         *         Log.d(TAG, it)
         *     }
         *
         * flatMapMerge：让流并发进行
         * transform：转换操作符
         */
        launchUI {
            flow {
                (10 downTo 0).forEach {
                    delay(1000)
                    emit(it)
                }
            }
                .map {
                    "${it}s"
                }
                .flowOn(Dispatchers.Default)
                .onStart {
                    //开始
                    mTime.postValue("开始")
                    btnEnable.postValue(false)
                }
                .onCompletion {
                    //结束
                    mTime.postValue("结束")
                    btnEnable.postValue(true)
                }
                .collect {
                    //过程
                    mTime.postValue(it)
                }
        }
    }

}