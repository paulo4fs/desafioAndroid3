package com.paulo.myapplication.data.api

import javax.crypto.Cipher.PRIVATE_KEY

class ServiceRetrofit {

    companion object {
        fun getHash(): String {
            return ExtensionMarvelApi.md5(getTimeStamp() + getPrivateKey() + getPublicKey())
        }
    }
}