package com.evan.api

import com.evan.lib.entity.ResultData
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @description:
 * @author：GR
 * @date：2021/12/16  9:40
 */
interface ApiService {
    @POST("account/backend/login")
    suspend fun login(@Query("userName") userName: String, @Query("password") password: String): ResultData

}