package com.ellfors.mvvmtest.http.api

import com.ellfors.mvvmtest.bean.ArticlesBean
import com.ellfors.mvvmtest.bean.BaseResponse
import com.ellfors.mvvmtest.bean.Girl
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * HttpApi
 * 2020-05-06 17:11
 */
interface HttpService {

    @GET("https://gank.io/api/v2/random/category/Girl/type/Girl/count/1")
    suspend fun refreshImage(): BaseResponse<MutableList<Girl>>

    @GET("https://gank.io/api/v2/data/category/GanHuo/type/Android/page/{page}/count/10")
    suspend fun getArticles(
        @Path("page") path: Int
    ): BaseResponse<MutableList<ArticlesBean>>
}