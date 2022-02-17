package com.evan.lib.base.adapter

import androidx.viewbinding.ViewBinding

/**
 * @description:
 * @author：Evan
 * @date：2022/2/17 16:20
 */
class BaseBindingHolder<VB : ViewBinding>(var binding: VB) : BaseViewHolder(binding.root)
