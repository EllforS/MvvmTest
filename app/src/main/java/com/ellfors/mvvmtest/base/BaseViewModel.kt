package com.ellfors.mvvmtest.base

import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ellfors.mvvmtest.app.MyApp
import com.ellfors.mvvmtest.bean.BaseResponse
import com.ellfors.mvvmtest.utils.TsUtil
import kotlinx.coroutines.*
import java.lang.Exception

/**
 * BaseViewModel
 * 2020-05-19 15:15
 */
open class BaseViewModel : ViewModel() {

    val mException = MutableLiveData<BaseException>()

    /**
     * 网络请求协程
     * 返回数据本身
     */
    fun <T> launchIO(
        block: suspend CoroutineScope.() -> BaseResponse<T>,
        success: (T) -> Unit,
        error: (BaseException) -> Unit = { },
        complete: () -> Unit = {}
    ) {
        launchUI {
            handleException(
                { withContext(Dispatchers.IO) { block() } },
                { res ->
                    executeResponse(res) { success(it) }
                    mException.postValue(BaseException())
                },
                {
                    error(it)
                    mException.postValue(it)
                    if (!it.message.isNullOrEmpty())
                        TsUtil.showToast(it.message)
                },
                {
                    complete()
                }
            )
        }
    }

    /**
     * 网络请求协程
     * 返回BaseResponse
     */
    fun <T> launchIOByBase(
        block: suspend CoroutineScope.() -> BaseResponse<T>,
        success: (BaseResponse<T>) -> Unit,
        error: (BaseException) -> Unit = { },
        complete: () -> Unit = {}
    ) {
        launchUI {
            handleException(
                { withContext(Dispatchers.IO) { block() } },
                { res ->
                    coroutineScope {
                        if (res.status == 100)
                            success(res)
                        else
                            throw Exception("这里是Msg")
                    }
                    mException.postValue(BaseException())
                },
                {
                    error(it)
                    mException.postValue(it)
                    if (!it.message.isNullOrEmpty())
                        TsUtil.showToast(it.message)
                },
                {
                    complete()
                }
            )
        }
    }

    /**
     * UI协程
     */
    fun launchUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            block()
        }
    }

    /*
    ***************************************************************************************
     */

    /**
     * 异常统一处理
     */
    private suspend fun <T> handleException(
        block: suspend CoroutineScope.() -> BaseResponse<T>,
        success: suspend CoroutineScope.(BaseResponse<T>) -> Unit,
        error: suspend CoroutineScope.(BaseException) -> Unit,
        complete: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                success(block())
            } catch (e: Exception) {
                if (e is BaseException)
                    error(e)
                else
                    error(BaseException(e.message, -100))
            } finally {
                complete()
            }
        }
    }

    /**
     * 请求结果过滤
     */
    private suspend fun <T> executeResponse(
        response: BaseResponse<T>,
        success: suspend CoroutineScope.(T) -> Unit
    ) {
        coroutineScope {
            if (response.status == 100)
                success(response.data)
            else
                throw Exception("这里是Msg")
        }
    }
}