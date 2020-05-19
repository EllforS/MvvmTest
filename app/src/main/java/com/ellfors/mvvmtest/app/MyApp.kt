package com.ellfors.mvvmtest.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * MyApp
 * 2020-05-19 11:04
 */
class MyApp : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}