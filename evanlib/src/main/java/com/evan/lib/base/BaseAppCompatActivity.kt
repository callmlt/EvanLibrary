package com.evan.lib.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.evan.lib.R
import com.evan.lib.databinding.CommonTitleViewBinding
import com.evan.lib.util.InputMethodMemoryUtil
import com.evan.lib.view.dialog.EvanLoadDialog

/**
 * @description:
 * @author：Evan
 * @date：2022/1/14  12:00
 */
abstract class BaseAppCompatActivity<T : ViewBinding> : AppCompatActivity() {
    private var _binding: T? = null
    val binding get() = _binding!!

    private val loadDialog: EvanLoadDialog by lazy {
        EvanLoadDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        beforeView()
        setContentView(binding.root)
        initViews()
        initData()
    }

    /**
     * 加载布局文件之前执行
     * 即setContentView 之前执行
     */
    protected open fun beforeView() {

    }

    /**
     * 初始化Views
     */
    protected open fun initViews() {

    }

    /**
     * 加载数据
     */
    protected open fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            _binding = null;
            dismissLoadingDialog()
            InputMethodMemoryUtil.fixInputMethodManagerLeak(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 显示对话框
     */
    fun showLoadingDialog() {
        try {
            if (!loadDialog.isShowing) {
                loadDialog.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 隐藏对话框
     */
    fun dismissLoadingDialog() {
        try {
            if (loadDialog.isShowing) {
                loadDialog.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    protected abstract fun getViewBinding(): T

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fadein_right, R.anim.fadeout_right)
    }


    protected fun initHeadViews( commonTitleViewBinding: CommonTitleViewBinding,title: String ) {
        commonTitleViewBinding.titleTx.text=title;
        commonTitleViewBinding.backImg.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    protected fun initHeadViews( commonTitleViewBinding: CommonTitleViewBinding,title: String,onClickListener: View.OnClickListener) {
        commonTitleViewBinding.titleTx.text=title;
        commonTitleViewBinding.backImg.setOnClickListener(onClickListener)
    }
}