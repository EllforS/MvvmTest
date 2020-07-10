package com.ellfors.mvvmtest.http

import android.util.Log
import com.ellfors.mvvmtest.http.interceptor.ResponseAddUrlInterceptor
import com.ellfors.mvvmtest.utils.HttpLogUtil
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * HttManager
 * 2020-05-06 16:39
 */
class HttpManager {
    companion object {
        @Volatile
        private var instance: HttpManager? = null

        fun getInstance(): HttpManager =
            instance ?: synchronized(this) {
                instance ?: HttpManager().also {
                    instance = it
                }
            }
    }

    private var okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    init {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            HttpLogUtil.log("HttpTag", it)
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(ResponseAddUrlInterceptor())
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }

    /**
     * 获取Gson解析的HttpApi
     */
    fun <T> getGsonHttpApi(clz: Class<T>): T {
        return Retrofit.Builder()
            .client(okHttpClientBuilder.build())
            .baseUrl("https://gank.io")
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(clz)
    }
}