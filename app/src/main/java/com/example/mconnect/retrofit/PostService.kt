package com.example.mconnect.retrofit

import com.example.mconnect.MRetrofitBuilder
import com.example.mconnect.connect.MCall

interface PostService {
    @MRetrofitBuilder.POST("user/login/?username=xigua&password=123456")
    fun post():MCall
}