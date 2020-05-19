package com.ellfors.mvvmtest.http.api

import com.ellfors.mvvmtest.bean.ArticlesBean
import com.ellfors.mvvmtest.bean.BaseResponse
import com.ellfors.mvvmtest.bean.Girl
import retrofit2.http.GET

/**
 * HttpApi
 * 2020-05-06 17:11
 */
interface HttpService {

    @GET("https://gank.io/api/v2/random/category/Girl/type/Girl/count/1")
    suspend fun refreshImage(): BaseResponse<List<Girl>>

    @GET("https://gank.io/api/v2/random/category/GanHuo/type/Android/count/10")
    suspend fun getArticles(): BaseResponse<List<ArticlesBean>>
}