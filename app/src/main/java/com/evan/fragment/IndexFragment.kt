package com.evan.fragment

import android.content.Intent
import com.evan.activity.TestLoadActivity
import com.evan.databinding.FragmentIndexBinding

/**
 * @description: 首页
 * @author：GR
 * @date：2022/1/17  10:13
 */
class IndexFragment : BaseKotlinFragment<FragmentIndexBinding>() {
    override fun getViewBinding(): FragmentIndexBinding {
        return FragmentIndexBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        binding.loadBtn.setOnClickListener { startActivity(Intent(context, TestLoadActivity::class.java)) }
    }
}