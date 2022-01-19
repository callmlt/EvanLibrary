package com.evan.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.evan.R
import com.evan.databinding.ItemMainViewBinding

class MainItemAdapter(data: ArrayList<String>?) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_main_view, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        val viewBinding = ItemMainViewBinding.bind(helper.itemView)
        viewBinding.nameTx.text = item
    }
}
