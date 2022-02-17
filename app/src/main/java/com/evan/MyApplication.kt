package com.evan

import android.app.Application
import com.evan.lib.net.NetConfig
import com.evan.lib.net.interceptor.ResponseDecryptInterceptor
import com.evan.lib.vm.AppViewModel
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter

/**
 * @description:
 * @author：GR
 * @date：2021/12/16  9:34
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppViewModel().init(this)
        NetConfig.builder().setBaseUrl("http://117.41.200.69:20600")
                .addOkHttpInterceptor(ResponseDecryptInterceptor())
                .setMockUrl("http://117.41.200.69:20600").init(this)
        initRefreshCreator()

    }

    private fun initRefreshCreator() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator({ context, layout ->
            //ClassicsHeader经典   layout.setPrimaryColorsId(R.color.gray4, R.color.gray66);//全局设置主题颜色 默认是 贝塞尔雷达Header
            MaterialHeader(context)
        })
        SmartRefreshLayout.setDefaultRefreshFooterCreator({ context, layout ->
            //指定为经典Footer，默认是 BallPulseFooter
            ClassicsFooter(context).setDrawableSize(20f)
        })
    }
}