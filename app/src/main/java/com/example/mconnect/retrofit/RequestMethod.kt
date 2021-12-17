package com.example.mconnect.retrofit

import com.example.mconnect.connect.MCall

abstract class RequestMethod {

    abstract fun invoke(args: Array<out Any>?):MCall
}