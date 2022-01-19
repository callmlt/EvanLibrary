package com.evan.lib.net.interceptor

import com.evan.lib.util.JsonUtils
import com.evan.lib.util.LogUtil
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import java.nio.charset.Charset

/**
 * @description: 对响应数据进行解密
 * @author：Evan
 * @date：2021/12/17  10:58
 */
class ResponseDecryptInterceptor :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var request=chain.request()
        var response=chain.proceed(request)
        if (response.isSuccessful) {
            val responseBody = response.body
            if (responseBody  != null) {
                /*开始解密*/
                try {
                    val source = responseBody.source()
                    source.request(java.lang.Long.MAX_VALUE)
                    val buffer = source.buffer()
                    var charset = Charset.forName("UTF-8")
                    val contentType = responseBody.contentType()
                    if (contentType != null) {
                        charset = contentType.charset(charset)
                    }
                    val bodyString = buffer.clone().readString(charset)
                    val jsonObject = JsonUtils.createJSONObject(bodyString)
                    //这里是解密过程
                    if(jsonObject.has("body")){

                    }
                    /*将解密后的明文返回*/
                    val newResponseBody = bodyString.toResponseBody(contentType)
                    response = response.newBuilder().body(newResponseBody).build()
                } catch (e: Exception) {
                    /*异常说明解密失败 信息被篡改 直接返回即可 */
                    LogUtil.e("解密失败");
                    return response
                }
            }
        }
        return response;
    }

}