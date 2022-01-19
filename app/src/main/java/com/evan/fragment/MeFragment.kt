package com.evan.fragment

import com.evan.databinding.FragmentMeBinding

/**
 * @description: 个人中心页面
 * @author：GR
 * @date：2022/1/17  10:54
 */
class MeFragment : BaseKotlinFragment<FragmentMeBinding>() {
    override fun getViewBinding(): FragmentMeBinding {
        return FragmentMeBinding.inflate(layoutInflater)
    }
}