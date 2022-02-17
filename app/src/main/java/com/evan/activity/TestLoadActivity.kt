package com.evan.activity

import androidx.lifecycle.ViewModelProvider
import com.evan.adapter.MainItemAdapter
import com.evan.databinding.ActivityTestLoadBinding
import com.evan.fragment.BottomDialogFragmentTest
import com.evan.lib.base.adapter.BaseEvanAdapter
import com.evan.lib.util.LogUtil
import com.evan.lib.util.ToastUtil
import com.evan.lib.util.WeakHandler
import com.evan.lib.vm.AppViewModel
import com.evan.model.MainModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

/**
 * @description:
 * @author：GR
 * @date：2022/1/17 10:28
 */
class TestLoadActivity:BaseKotlinActivity<ActivityTestLoadBinding>(){
    override fun getViewBinding(): ActivityTestLoadBinding {
         return ActivityTestLoadBinding.inflate(layoutInflater)
    }
    private var adapter: MainItemAdapter? = null
    private var list = ArrayList<String>()

    private var mainModel: MainModel? = null
    private var weakHandler: WeakHandler? = null


    override fun initViews() {
        super.initViews()
        initHeadViews(binding.includeHead, "测试")
        mainModel = AppViewModel.getViewModel(MainModel::class.java)

        setLinearLayoutManager(binding.mRecyclerView)
        adapter = MainItemAdapter(list)
        binding.mRecyclerView.adapter = adapter
        weakHandler = WeakHandler();


        //weakHandler 的使用
        weakHandler!!.postDelayed(Runnable {
            LogUtil.e("postDelayed")
            ToastUtil.showToast(this.applicationContext,"postDelayed")
        }, 5000)

        adapter!!.setOnItemClickListener(BaseEvanAdapter.OnItemClickListener { adapter, view, position ->
            when {
                position == 0 -> mainModel?.login("156116565", "4564654")
                position == 1 -> BottomDialogFragmentTest().show(supportFragmentManager, "sdfsdf")
                position == 2 -> showLoadingDialog()
            }
        })
        setSwipeBackEnable(false)
    }



    override fun initData() {
        super.initData()
        list.add("login request")
        list.add("BottomDialogFragment 底部弹出框使用")
        list.add("loading")
        list.add("dismiss loading")
        adapter!!.notifyDataSetChanged()
    }


    suspend fun setImage() {
        var job = GlobalScope.launch {
            delay(1000)
        }
        job.join()
    }


}
