package com.evan

import android.app.Application
import com.evan.lib.net.NetConfig
import com.evan.lib.net.interceptor.ResponseDecryptInterceptor
import com.evan.lib.vm.AppViewModel

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

    }
}