package com.evan

//import kotlinx.android.synthetic.main.activity_main.*
import com.chad.library.adapter.base.BaseQuickAdapter
import com.evan.activity.BaseKotlinActivity
import com.evan.adapter.MainItemAdapter
import com.evan.databinding.ActivityMainBinding
import com.evan.fragment.BottomDialogFragmentTest
import com.evan.model.MainModel
import com.evan.view.helper.MainHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

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
