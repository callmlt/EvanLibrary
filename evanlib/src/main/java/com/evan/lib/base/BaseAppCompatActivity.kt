package com.evan.lib.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.evan.lib.R
import com.evan.lib.databinding.CommonTitleViewBinding
import com.evan.lib.util.InputMethodMemoryUtil
import com.evan.lib.view.dialog.EvanLoadDialog
import com.evan.lib.view.swipeback.SwipeBackActivityBase
import com.evan.lib.view.swipeback.SwipeBackActivityHelper
import com.evan.lib.view.swipeback.SwipeBackLayout
import com.evan.lib.view.swipeback.Utils

/**
 * @description:
 * @author：Evan
 * @date：2022/1/14  12:00
 */
abstract class BaseAppCompatActivity<T : ViewBinding> : AppCompatActivity(), SwipeBackActivityBase {
    private var _binding: T? = null
    val binding get() = _binding!!

    private lateinit var mSwipeBackLayout: SwipeBackLayout
    lateinit var helper: SwipeBackActivityHelper

    private val loadDialog: EvanLoadDialog by lazy {
        EvanLoadDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        beforeView()
        setContentView(binding.root)
        initSwipeBack()
        initViews()
        initData()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        helper.onPostCreate()
    }

    // 初始化 【右滑返回】
    private fun initSwipeBack() {
        helper = SwipeBackActivityHelper(this)
        helper.onActivityCreate()
        mSwipeBackLayout = swipeBackLayout
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT)
    }

    /**
     * @param enableSwipeBack
     * @param edgeFlags 设置滑动方向，可设置 EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
     */
    fun swipeBack(enableSwipeBack: Boolean, edgeFlags: Int) {
        mSwipeBackLayout.setEdgeTrackingEnabled(edgeFlags)
        setSwipeBackEnable(enableSwipeBack)
    }

    override fun getSwipeBackLayout(): SwipeBackLayout {
        return helper.getSwipeBackLayout()
    }


    override fun setSwipeBackEnable(enable: Boolean) {
        mSwipeBackLayout.setEnableGesture(enable)
    }

    override fun scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this)
        mSwipeBackLayout.scrollToFinishActivity()
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
            swipeBackLayout.scrollToFinishActivity()
            InputMethodMemoryUtil.fixInputMethodManagerLeak(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**** 显示对话框*/
    fun showLoadingDialog() {
        try {
            if (!loadDialog.isShowing) {
                loadDialog.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**** 隐藏对话框  */
    fun dismissLoadingDialog() {
        try {
            if (loadDialog.isShowing) {
                loadDialog.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /****获取ViewBinding  */
    protected abstract fun getViewBinding(): T

    /****页面返回*/
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fadein_right, R.anim.fadeout_right)
    }

    /**
     * 初始化标题
     * @param commonTitleViewBinding viewBinding对象
     * @param title 标题
     */
    protected fun initHeadViews( commonTitleViewBinding: CommonTitleViewBinding,title: String ) {
        commonTitleViewBinding.titleTx.text=title;
        commonTitleViewBinding.backImg.setOnClickListener {
            finish()
        }
    }

    /**
     * 初始化标题
     * @param commonTitleViewBinding viewBinding对象
     * @param title 标题
     * @param onClickListener 点击事件
     */
    protected fun initHeadViews(commonTitleViewBinding: CommonTitleViewBinding, title: String, onClickListener: View.OnClickListener) {
        commonTitleViewBinding.titleTx.text=title;
        commonTitleViewBinding.backImg.setOnClickListener(onClickListener)
    }
}