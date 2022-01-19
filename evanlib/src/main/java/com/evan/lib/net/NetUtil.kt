package com.evan.lib.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @description:
 * @author：GR
 * @date：2021/12/15  15:16
 */
object NetUtil {

    private lateinit var retrofit: Retrofit

    fun init() {
        var builder = OkHttpClient.Builder()

        NetConfig.getOkHttpInterceptors()?.let {
            for (interceptor in it) {
                builder.addInterceptor(interceptor)
            }
        }

        var okHttpClient = builder.addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        retrofit = Retrofit.Builder().baseUrl(NetConfig.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()
    }

    fun <T> getService(service: Class<T>): T {
        return retrofit.create(service)
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    fun <T> request(
            block: suspend CoroutineScope.() -> T?,
            onStart: () -> Unit = {},
            onSuccess: (T?) -> Unit = {},
            onError: (Throwable) -> Unit = {},
            onComplete: () -> Unit = {}
    ): Job {
        //flow会导致findViewById为空
        return GlobalScope.launch(Dispatchers.Main) {
            flow {
                emit(block())
            }.flowOn(Dispatchers.IO)
                    .onStart {
                        onStart()
                    }.onCompletion {
                        onComplete()
                    }.catch { throwable ->
                        onError(throwable)
                    }.collect { res ->
                        onSuccess(res)
                    }
        }

    }


}

