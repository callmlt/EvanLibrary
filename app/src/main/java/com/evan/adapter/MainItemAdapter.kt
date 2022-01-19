package com.evan.adapter
import com.evan.R
import com.evan.databinding.ItemMainViewBinding
import com.evan.lib.base.adapter.BaseEvanAdapter
import com.evan.lib.base.adapter.BaseViewHolder

class MainItemAdapter(data: ArrayList<String>?) : BaseEvanAdapter<String, BaseViewHolder>(R.layout.item_main_view, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        val viewBinding = ItemMainViewBinding.bind(helper.itemView)
        viewBinding.nameTx.text = item
    }
}
