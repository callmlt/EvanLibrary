package com.evan.lib.base

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.evan.lib.R
import com.evan.lib.databinding.CommonTitleViewBinding
import com.evan.lib.util.InputMethodMemoryUtil
import com.evan.lib.view.EvanLoadDialog

/**
 * @description:
 * @author：Evan
 * @date：2022/1/14  12:00
 */
abstract class BaseAppCompatActivity<T : ViewBinding> : AppCompatActivity() {
    private var _binding: T? = null
    val binding get() = _binding!!

    protected var backImg: ImageView? = null
    protected var titleTx: TextView? = null
    protected var saveTx: TextView? = null

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
    fun showLoadingDialog() {
        try {
            if (!loadDialog.isShowing) {
                loadDialog.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

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

    protected fun initHeadViews(root: View, title: String) {
        initHeadViews(root, title, View.OnClickListener {
            finish()
        })
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


    protected fun initHeadViews(root: View, title: String, onClickListener: View.OnClickListener) {
        titleTx = root.findViewById(R.id.titleTx)
        if (titleTx == null) {
            return;
        }
        titleTx!!.text = title;
        backImg = root.findViewById(R.id.backImg);
        saveTx = root.findViewById(R.id.saveTx);
        this.backImg!!.setOnClickListener(onClickListener)
    }
}