package com.evan.lib.view.dialog

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.evan.lib.R

/**
 * @description:
 * @author：Evan
 * @date：2022/2/17 10:08
 */
abstract class BaseBottomBindDialog<VB : ViewBinding> : BaseBindDialog<VB> {


    constructor(context: Context) : super(context, R.style.BaseDialog) {
    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId) {}

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) : super(context, cancelable, cancelListener) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setCancelable(false)
        binding = getViewBinding()
        window!!.setWindowAnimations(R.style.BottomDialog_Animation)
        window!!.setGravity(Gravity.BOTTOM)
        setContentView(binding.root)
        val params = binding.root.getLayoutParams() as ViewGroup.MarginLayoutParams
        params.width = context.getResources().getDisplayMetrics().widthPixels
        params.bottomMargin = 0
        binding.root.setLayoutParams(params)
        initialize(savedInstanceState)

    }
}
