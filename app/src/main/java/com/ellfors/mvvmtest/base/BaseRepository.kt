package com.ellfors.mvvmtest.base

import com.ellfors.mvvmtest.http.HttpManager
import com.ellfors.mvvmtest.http.api.HttpService

/**
 * BaseRepository
 * 2020-05-19 15:16
 */
open class BaseRepository {

    val getHttpService = HttpManager.instance.getGsonHttpApi(HttpService::class.java)
}