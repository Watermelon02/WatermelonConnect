package com.example.mconnect.connect

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object MConnect {
    var cookies: List<String>? = null
    fun get(url: String): Response {
        return (URL(url).openConnection() as HttpURLConnection).run {
            if (cookies != null) {
                val cookie = java.lang.StringBuilder()
                for (srt in cookies!!) {
                    cookie.append(srt).append(";")
                }
                setRequestProperty("Cookie", cookie.toString())
            }
            doInput = true
            connectTimeout = 15000
            readTimeout = 15000
            requestMethod = "GET"
            setRequestProperty("Connection", "Keep-Alive")
            connect()
            val builder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.use { reader.forEachLine { builder.append(it) } }
            Response(builder.toString())
        }
    }

    fun post(url: String): Response {
        return (URL(url).openConnection() as HttpURLConnection).run {
            if (cookies != null) {
                val cookie = java.lang.StringBuilder()
                for (srt in cookies!!) {
                    cookie.append(srt).append(";")
                }
                setRequestProperty("Cookie", cookie.toString())
            }
            doInput = true
            doOutput = true
            connectTimeout = 15000
            readTimeout = 15000
            requestMethod = "POST"
            setRequestProperty("Connection", "Keep-Alive")
            connect()
            val header: Map<String, List<String>> = headerFields
            cookies = header["Set-cookie"]
            val reader = BufferedReader(InputStreamReader(inputStream))
            val builder = StringBuilder()
            reader.use { it.forEachLine { builder.append(it) } }
            Response(builder.toString())
        }
    }


}