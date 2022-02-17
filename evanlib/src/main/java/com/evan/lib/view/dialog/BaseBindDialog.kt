package com.evan.lib.view.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import androidx.viewbinding.ViewBinding
import com.evan.lib.R
import com.evan.lib.util.ToastUtil

/**
 * @description:
 * @author：Evan
 * @date：2022/2/17 10:08
 */
abstract class BaseBindDialog<VB : ViewBinding> : Dialog {
    protected lateinit var binding: VB

    constructor(context: Context) : super(context, R.style.BaseDialog) {
    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId) {}

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) : super(context, cancelable, cancelListener) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setCancelable(false)
        binding = getViewBinding()
        setContentView(binding.root)
        initialize(savedInstanceState)
    }

    fun showToast(message: String) {
        ToastUtil.showToast(context, message, false, Gravity.CENTER)
    }

    protected abstract fun getViewBinding(): VB
    protected abstract fun initialize(savedInstanceState: Bundle?)

}
