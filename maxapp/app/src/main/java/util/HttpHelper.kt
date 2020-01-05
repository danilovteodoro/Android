package util

import okhttp3.OkHttpClient

import okhttp3.Request
import java.lang.Exception

class HttpHelper {
    private val client: OkHttpClient = OkHttpClient()

    fun getRequest(url: String): Request {
        return Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .build()
    }

    fun executeGet(url:String): String {
        val request = getRequest(url)
        client.newCall(request).execute().use { response ->
            if (response.isSuccessful) {
                return response.body!!.string()
            }
            throw HttpException(response.code, response.message, request.url.toString())
        }
    }


    class HttpException(var code: Int, message: String, var url: String = "") : Exception(message)
}

