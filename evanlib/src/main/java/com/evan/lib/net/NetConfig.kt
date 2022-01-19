package com.evan.lib.net

import android.app.Application
import com.evan.lib.net.interceptor.ErrorInterceptor
import com.evan.lib.util.AppUtil
import okhttp3.Interceptor
import java.lang.RuntimeException

/**
 * @description:
 * @author：GR
 * @date：2021/12/15  15:27
 */
object NetConfig {

    private var baseUrl: String? = null
    private var mockUrl: String? = null

    //okHttp拦截器
    private var okHttpInterceptors: MutableList<Interceptor>? = null

    //接口返回不正常状态拦截器
    private var retCodeInterceptors: MutableList<ErrorInterceptor>? = null

    private var configBuilder: ConfigBuilder? = null

    fun getOkHttpInterceptors(): List<Interceptor>? {
        return okHttpInterceptors;
    }

    fun getBaseUrl(): String {
        return baseUrl?:throw RuntimeException("baseUrl is null")
    }

    fun getMockUrl(): String? {
        return mockUrl
    }

    @Synchronized
    fun builder(): ConfigBuilder {
        if (configBuilder == null) {
            configBuilder = ConfigBuilder();
        }
        return configBuilder as ConfigBuilder;
    }

    class ConfigBuilder {
        fun setBaseUrl(baseUrl: String?): ConfigBuilder {
            NetConfig.baseUrl = baseUrl
            return this
        }

        fun setMockUrl(basUrl: String?): ConfigBuilder {
            NetConfig.mockUrl = mockUrl
            return this
        }

        fun addOkHttpInterceptor(okHttpInterceptor: Interceptor): ConfigBuilder {
            if (okHttpInterceptors == null) {
                okHttpInterceptors = ArrayList()
            }
            okHttpInterceptors?.add(okHttpInterceptor)
            return this
        }

        fun init(application: Application): ConfigBuilder {
            AppUtil.init(application)
            NetUtil.init()
            return this;
        }
    }

}