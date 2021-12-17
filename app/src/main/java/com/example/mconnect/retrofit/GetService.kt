package com.example.mconnect.retrofit

import com.example.mconnect.MRetrofitBuilder
import com.example.mconnect.connect.MCall

interface GetService {
    @MRetrofitBuilder.GET("/user/lg/userinfo/json")
    fun get():MCall
}