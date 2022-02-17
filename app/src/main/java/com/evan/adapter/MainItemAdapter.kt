package com.evan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.evan.R
import com.evan.databinding.ItemMainViewBinding
import com.evan.lib.base.adapter.BaseBindingAdapter
import com.evan.lib.base.adapter.BaseBindingHolder

class MainItemAdapter(data: ArrayList<String>) : BaseBindingAdapter<String, BaseBindingHolder<ItemMainViewBinding>>(R.layout.item_main_view, data) {
    override fun convert(helper: BaseBindingHolder<ItemMainViewBinding>, item: String?) {
         helper.binding.nameTx.text=item
    }

    override fun getViewBinding(viewType: Int, from: LayoutInflater, parent: ViewGroup): ViewBinding {
        return ItemMainViewBinding.inflate(from, parent, false)
    }


}
