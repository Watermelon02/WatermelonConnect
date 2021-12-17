package com.example.mconnect.connect

import java.lang.Exception

class MCall(private val request: Request) {
    fun enqueue(onResponse: (Response) -> Unit, onFailure: (Exception) -> Unit) {
        when (request.method) {
            "GET" -> {
                Thread {
                    try {
                        val response = MConnect.get(request.url)
                        onResponse(response)
                    } catch (e: Exception) {
                        onFailure(e)
                    }
                }.start()
            }
            "POST" -> {
                Thread {
                    try {
                        val response = MConnect.post(request.url)
                        onResponse(response)
                    } catch (e: Exception) {
                        onFailure(e)
                    }
                }.start()
            }
        }
    }
}

interface MCallback {
    fun onResponse(response: Response)
    fun onFailure(e: Exception)
}