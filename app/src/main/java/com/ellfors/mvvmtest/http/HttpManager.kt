package com.ellfors.mvvmtest.http

import com.ellfors.mvvmtest.http.interceptor.ResponseAddUrlInterceptor
import okhttp3.OkHttpClient
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
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(ResponseAddUrlInterceptor())
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