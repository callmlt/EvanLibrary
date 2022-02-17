package com.evan.lib.view.dialog

import android.content.Context
import android.os.Bundle
import com.evan.lib.R
import com.evan.lib.databinding.DialogEvanLoadBinding

/**
 * @description: 加载框
 * @author：Evan
 * @date：2022/1/14 11:48
 * <color name="loadingColor">#333333</color>
 */
class EvanLoadDialog(context: Context) : BaseBindDialog<DialogEvanLoadBinding>(context, R.style.loadingDialogStyle) {

    override fun getViewBinding(): DialogEvanLoadBinding {
        return DialogEvanLoadBinding.inflate(layoutInflater)
    }

    override fun initialize(savedInstanceState: Bundle?) {
       // setCanceledOnTouchOutside(false)
        setCancelable(false)
    }
}
