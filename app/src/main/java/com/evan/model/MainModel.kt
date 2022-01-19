package com.evan.model

import androidx.lifecycle.ViewModel
import com.evan.api.ApiService
import com.evan.lib.net.NetUtil
import com.evan.lib.vm.IViewModel

/**
 * @description:
 * @author：GR
 * @date：2021/12/16  9:48
 */
class MainModel : IViewModel, ViewModel() {

    fun login(userName: String, password: String) {
        NetUtil.request(block = {
            NetUtil.getService(ApiService::class.java).login(userName, password).data
        })
    }


}