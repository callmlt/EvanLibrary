package com.evan.lib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.evan.lib.view.EvanLoadDialog

/**
 * @description: 所有Fragment的基类
 * @author：Evan
 * @date：2022/1/14 17:00
 */
abstract class BaseFragment<T : ViewBinding> : Fragment() {
    private var _binding: T? = null
    val binding get() = _binding!!

    private val loadDialog: EvanLoadDialog by lazy {
        EvanLoadDialog(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = getViewBinding()
        initViews()
        initData()
        return binding.root
    }

    protected abstract fun getViewBinding(): T
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

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            _binding = null
            dismissLoadingDialog()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
