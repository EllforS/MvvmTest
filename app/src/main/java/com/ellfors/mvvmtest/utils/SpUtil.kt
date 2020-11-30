package com.ellfors.mvvmtest.utils

import android.os.Parcelable
import com.tencent.mmkv.MMKV

/**
 * SpUtil
 * 2020-11-30 16:01
 */
class SpUtil(id: String?) {

    companion object {

        @Volatile
        private var instance: SpUtil? = null

        fun getInstance(): SpUtil {
            if (instance == null) {
                synchronized(SpUtil::class.java) {
                    if (instance == null) {
                        instance = SpUtil(null)
                    }
                }
            }
            return instance!!
        }

        fun getInstance(id: String?): SpUtil {
            if (instance == null) {
                synchronized(SpUtil::class.java) {
                    if (instance == null) {
                        instance = SpUtil(id)
                    }
                }
            }
            return instance!!
        }
    }

    private val kv by lazy {
        if (id.isNullOrEmpty())
            MMKV.defaultMMKV()
        else
            MMKV.mmkvWithID(id)
    }

    fun saveData(key: String?, data: Any?) {
        if (key.isNullOrEmpty())
            return
        when (data) {
            is String ->
                kv.encode(key, data)
            is Int ->
                kv.encode(key, data)
            is Long ->
                kv.encode(key, data)
            is Float ->
                kv.encode(key, data)
            is Double ->
                kv.encode(key, data)
            is Boolean ->
                kv.encode(key, data)
            is Parcelable ->
                kv.encode(key, data)
        }
    }

    fun saveData(key: String?, data: Set<String>?) {
        if (key.isNullOrEmpty() || data == null)
            return
        kv.encode(key, data)
    }

    fun getString(key: String?): String? {
        if (key.isNullOrEmpty())
            return ""
        return kv.decodeString(key, "")
    }

    fun getInt(key: String?): Int {
        if (key.isNullOrEmpty())
            return 0
        return kv.decodeInt(key, 0)
    }

    fun getLong(key: String?): Long {
        if (key.isNullOrEmpty())
            return 0
        return kv.decodeLong(key, 0)
    }

    fun getFloat(key: String?): Float {
        if (key.isNullOrEmpty())
            return 0f
        return kv.decodeFloat(key, 0f)
    }

    fun getDouble(key: String?): Double {
        if (key.isNullOrEmpty())
            return 0.00
        return kv.decodeDouble(key, 0.00)
    }

    fun getBoolean(key: String?): Boolean {
        if (key.isNullOrEmpty())
            return false
        return kv.decodeBool(key, false)
    }

    fun <T : Parcelable> getParcelable(key: String?, clz: Class<T>): T? {
        if (key.isNullOrEmpty())
            return null
        return kv.decodeParcelable(key, clz)
    }

    fun removeKey(key: String?) {
        if (key.isNullOrEmpty())
            return
        kv.removeValueForKey(key)
    }

    fun clearAll() {
        kv.clearAll()
    }

}