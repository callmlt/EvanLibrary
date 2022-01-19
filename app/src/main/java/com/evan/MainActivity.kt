package com.evan

//import kotlinx.android.synthetic.main.activity_main.*
import com.evan.activity.BaseKotlinActivity
import com.evan.databinding.ActivityMainBinding
import com.evan.view.helper.MainHelper

class MainActivity : BaseKotlinActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private var mainHelper:MainHelper?=null

    override fun initViews() {
        super.initViews()
        mainHelper= MainHelper(this)
        mainHelper!!.initBottomNavigationView(binding.bottomNavigationView,supportFragmentManager)
    }
}
