package com.evan.activity

import com.evan.adapter.MainItemAdapter
import com.evan.databinding.ActivityTestLoadBinding
import com.evan.fragment.BottomDialogFragmentTest
import com.evan.lib.base.adapter.BaseEvanAdapter
import com.evan.lib.util.WeakHandler
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
        mainModel = MainModel()

        setLinearLayoutManager(binding.mRecyclerView)
        adapter = MainItemAdapter(list)
        binding.mRecyclerView.adapter = adapter
        weakHandler = WeakHandler();

        //weakHandler 的使用
        weakHandler!!.postDelayed(Runnable {

        }, 5000)

        adapter!!.setOnItemClickListener(BaseEvanAdapter.OnItemClickListener { adapter, view, position ->
            when {
                position == 0 -> mainModel?.login("156116565", "4564654")
                position == 1 -> BottomDialogFragmentTest().show(supportFragmentManager, "sdfsdf")
                position == 2 -> showLoadingDialog()
                position == 3 -> dismissLoadingDialog()
            }
            this.mainModel?.login("15915459025", "12133131");
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
