package com.paulo.myapplication.data.api

class ServiceRetrofit {

    companion object {
        fun getHash(): String {
            val privateKey = "0dd0c16fedb8a02985977eafca66b49f5e6a526f"
            val publicKey = "6eb7e8896ec5850c52515a8a23ee97f0"

            return ExtensionMarvelApi.md5(getTimeStamp() + privateKey + publicKey)
        }
    }
}