package com.evan.activity

import android.annotation.SuppressLint
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.evan.R
import com.evan.lib.base.BaseAppCompatActivity
import com.evan.lib.util.AppStatusBarUtil

/**
 * @description:
 * @author：Evan
 * @date：2022/1/14 14:52
 */
abstract class BaseKotlinActivity<T : ViewBinding> : BaseAppCompatActivity<T>() {
    override fun initViews() {
        super.initViews()
        AppStatusBarUtil.setStatusBarMode(this, true, R.color.white)
    }

    @SuppressLint("WrongConstant")
    protected fun setLinearLayoutManager(recyclerView: RecyclerView) {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
    }

    protected fun setGridLayoutManager(recyclerView:RecyclerView,spanCount:Int){
        val gridLayoutManager=GridLayoutManager(this,spanCount)
        recyclerView.layoutManager=gridLayoutManager
    }
}
