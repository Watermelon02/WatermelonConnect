package com.example.mconnect

data class Hotkey(val data: List<Data>, val errorCode: Int, val errorMsg: String)
data class Data(val id: Int, val link: String, val name: String, val order: Int, val visible: Int)
data class User(var name: String,var password:String)
