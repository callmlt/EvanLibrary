package com.evan.lib.net.interceptor

/**
 * @description:
 * @author：GR
 * @date：2021/12/15  15:45
 */
abstract class ErrorInterceptor(var code: Int) {
    /**
     * 具体的拦截实现，
     * 如果返回值为true，则中断其他的错误处理
     */
    abstract fun interceptor(throwable: Throwable): Boolean
}