package com.ellfors.mvvmtest.http.adapter

import androidx.lifecycle.LiveData
import com.ellfors.mvvmtest.bean.BaseResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

@Suppress("UNCHECKED_CAST")
class LiveDataCallAdapter<T>(private val responseType: Type) : CallAdapter<T, LiveData<T>> {
    override fun adapt(call: Call<T>): LiveData<T> {
        return object : LiveData<T>() {
            private val started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                //确保执行一次
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<T> {
                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            //判断200
                            postValue(response.body())
                        }

                        override fun onFailure(call: Call<T>, t: Throwable) {
                            val value = BaseResponse<T>() as T
                            postValue(value)
                        }
                    })
                }
            }
        }
    }

    override fun responseType() = responseType
}