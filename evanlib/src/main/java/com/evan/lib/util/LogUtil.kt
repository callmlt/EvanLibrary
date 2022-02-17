package com.evan.lib.util

import android.util.Log

/**
 * @description:
 * @author：GR
 * @date：2021/12/17  11:08
 */
object LogUtil {
    var isDebug = true
    private var TAG = LogUtil.javaClass.simpleName;

    fun i(msg: String) {
        i(TAG, msg);
    }
    fun e(msg: String) {
        e(TAG, msg);
    }

    fun i(tag: String, msg: String) {
        if (!isDebug) {
            return
        }
        Log.i(tag, msg)
    }

    fun e(tag: String, msg: String) {
        if (!isDebug) {
            return
        }
        Log.e(tag, msg)
    }
}