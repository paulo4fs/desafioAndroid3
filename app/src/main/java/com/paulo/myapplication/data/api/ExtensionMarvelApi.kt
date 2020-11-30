package com.paulo.myapplication.data.api

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class ExtensionMarvelApi() {
    companion object {
        private val HEXCHARS = charArrayOf(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
        )

        private fun hexEncode(bytes: ByteArray): String {
            val result = CharArray(bytes.size * 2)
            var b: Int
            var i = 0
            var j = 0
            while (i < bytes.size) {
                b = bytes[i].toInt() and 0xff
                result[j++] = HEXCHARS[b shr 4]
                result[j++] = HEXCHARS[b and 0xf]
                i++
            }
            return String(result)
        }

        fun md5(s: String): String {
            try { // Create MD5 Hash
                val digest = MessageDigest.getInstance("MD5")
                digest.update(s.toByteArray())
                return hexEncode(digest.digest())
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
            return ""
        }
    }
}

fun getPublicKey() = "6eb7e8896ec5850c52515a8a23ee97f0"
fun getPrivateKey() = "0dd0c16fedb8a02985977eafca66b49f5e6a526f"
fun getTimeStamp() = (Calendar.getInstance().timeInMillis / 1000).toString()