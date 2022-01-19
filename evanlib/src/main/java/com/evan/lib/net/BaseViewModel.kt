package com.evan.lib.net

import androidx.lifecycle.*
import kotlinx.coroutines.*

//类型别名
typealias LaunchBlock = suspend CoroutineScope.() -> Unit

typealias EmitBlock<T> = suspend LiveDataScope<T>.() -> T

typealias Cancel = (e: Exception) -> Unit

/**
 * BaseViewModel
 * @author Evan 2021-12-17 15:38:00
 */
open class BaseViewModel : ViewModel() {
    //通用事件模型驱动(如：显示对话框、取消对话框、错误提示)
    private val mStateLiveData = MutableLiveData<StateActionEvent>()

    //使用协程封装统一的网络请求处理
    fun launch(cancel: Cancel? = null, block: LaunchBlock) {
        viewModelScope.launch {
            //ViewModel自带的viewModelScope.launch,会在页面销毁的时候自动取消请求,有效封装内存泄露
            try {
                mStateLiveData.value = LoadState
                block()
                mStateLiveData.value = SuccessState
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> cancel?.invoke(e)
                    else -> mStateLiveData.value = ErrorState(e.message)
                }
            }
        }
    }

    fun <T> emit(cancel: Cancel? = null, block: EmitBlock<T>): LiveData<T> = liveData {
        try {
            mStateLiveData.value = LoadState
            emit(block())
            mStateLiveData.value = SuccessState
        } catch (e: Exception) {
            when (e) {
                is CancellationException -> cancel?.invoke(e)
                else -> mStateLiveData.value = ErrorState(e.message)
            }
        }
    }
}