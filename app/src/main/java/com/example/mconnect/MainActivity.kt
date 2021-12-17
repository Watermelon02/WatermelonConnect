package com.example.mconnect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mconnect.retrofit.GetService
import com.example.mconnect.retrofit.PostService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = MRetrofitBuilder().baseUrl("https://wanandroid.com/").build()
        val getService = retrofit.create(GetService::class.java)
        val postService = retrofit.create(PostService::class.java)
        postService.post().enqueue({
            getService.get()
                .enqueue({ response -> Log.d("testResponse", response.data) },
                    { exception -> Log.d("testTag", exception.message.toString()) })
        },
            { exception -> Log.d("testTag", exception.message.toString()) })
    }
}