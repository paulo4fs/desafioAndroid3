package com.paulo.myapplication.data.api

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class ExtensionMarvelApi {
    companion object {
        private val hexChars = charArrayOf(
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
                result[j++] = hexChars[b shr 4]
                result[j++] = hexChars[b and 0xf]
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

fun getPublicKey() = "d5eb389c4ed264949086922b7b0c3545"
fun getPrivateKey() = "a8950be35e2b57fa68cc4b12ba1434c2e2a9e060"
fun getTimeStamp() = (Calendar.getInstance().timeInMillis / 1000).toString()