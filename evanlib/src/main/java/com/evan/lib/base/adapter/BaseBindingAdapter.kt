package com.evan.lib.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * @description:
 * @author：GR
 * @date：2022/2/10 16:53
 */
abstract class BaseBindingAdapter<T, VH : BaseViewHolder> : BaseEvanAdapter<T, VH> {

    constructor(layoutResId: Int, data: List<T>) : super(layoutResId, data) {}

    constructor(layoutResId: Int) : super(layoutResId) {}

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): VH {
        return BaseBindingHolder(getViewBinding(viewType, LayoutInflater.from(mContext), parent)) as VH
    }

    protected abstract fun getViewBinding(viewType: Int, from: LayoutInflater, parent: ViewGroup): ViewBinding

}