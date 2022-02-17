package com.evan.lib.util

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.Toast

/**
 * @author: evan
 * @description: Toast相关工具类
 */
object ToastUtil {
    var toast: Toast? = null
    fun showToast(
            context: Context,
            str: String,
            showLong: Boolean = false,
            gravity: Int = Gravity.CENTER
    ) {
        cancelToast(toast)
        toast = Toast.makeText(
                context.applicationContext, str,
                if (showLong) {
                    Toast.LENGTH_LONG
                } else {
                    Toast.LENGTH_SHORT
                }
        )
        toast?.setGravity(gravity, 0, 0)

        toast?.show()
    }

    fun showViewToast(
            context: Context,
            view: View,
            showLong: Boolean,
            gravity: Int = Gravity.CENTER
    ) {
        cancelToast(toast)
        if (toast == null) {
            toast = Toast(context.applicationContext)
        }
        toast?.setGravity(gravity, 0, 0)
        toast?.view = view
        toast?.duration = if (showLong) {
            Toast.LENGTH_LONG
        } else {
            Toast.LENGTH_SHORT
        }

        toast?.show()
    }

    /*** 取消前一次的弹窗*/
    fun cancelToast(toast: Toast?) {
        /**
         * tips: repeated using the same toast object to show, must cancel it first,
         * if not, maybe throw [IllegalStateException] in&above Android P,
         * refer https://stackoverflow.com/questions/51956971/illegalstateexception-of-toast-view-on-android-p-preview
         */
        try {
            if (toast != null && toast.view!!.isShown) {
                toast.cancel()
            }
        } catch (e: Exception) {
        }
    }
}