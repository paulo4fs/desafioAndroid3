package com.paulo.myapplication.data.api

class ServiceRetrofit {

    companion object {
        fun getHash(): String {
            return ExtensionMarvelApi.md5(getTimeStamp() + getPrivateKey() + getPublicKey())
        }
    }
}