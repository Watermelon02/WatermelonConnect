package com.example.mconnect

import com.example.mconnect.MRetrofitBuilder.GET
import com.example.mconnect.MRetrofitBuilder.POST
import com.example.mconnect.connect.MCall
import com.example.mconnect.connect.RequestBuilder
import com.example.mconnect.retrofit.RequestMethod
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class MRetrofitBuilder {
    annotation class GET(val url: String)
    annotation class POST(val url: String)

    private lateinit var baseurl: String

    fun baseUrl(baseurl: String): MRetrofitBuilder = apply {
        this.baseurl = baseurl
    }

    fun build() = MRetrofit(baseurl)
}

class MRetrofit(private val baseurl: String) {
    lateinit var url: String
    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(
            service.classLoader,
            arrayOf(service)
        ) { proxy, method, args -> loadServiceMethod(method).invoke(args) } as T
    }

    private fun loadServiceMethod(method: Method?): RequestMethod {
        lateinit var requestMethod: RequestMethod
        when (method?.let { parseAnnotation(it) }) {
            is GET -> {
                requestMethod = object : RequestMethod() {
                    override fun invoke(args: Array<out Any>?): MCall {
                        val request = RequestBuilder().method("GET").url(url).build()
                        return MCall(request)
                    }
                }
            }
            is POST -> {
                requestMethod = object : RequestMethod() {
                    override fun invoke(args: Array<out Any>?):MCall {
                        val request = RequestBuilder().method("POST").url(url).build()
                        return MCall(request)
                    }
                }
            }
        }
        return requestMethod
    }

    private fun parseAnnotation(method: Method): Annotation {
        val annotation = method.annotations[0]
        when (annotation) {
            is GET -> url = baseurl + annotation.url
            is POST -> url = baseurl + annotation.url
        }
        return annotation
    }
}