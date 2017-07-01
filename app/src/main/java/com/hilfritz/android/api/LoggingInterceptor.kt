package com.hilfritz.android.api

import android.util.Log
import com.hilfritz.android.util.network.RequestUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import java.io.IOException

/**
 * Created by Hilfritz Camallere on 16/6/17.
 *
 */
/**
 * Created by hilfritz on 26/11/16.
 * http://stackoverflow.com/questions/32965790/retrofit-2-0-how-to-print-the-full-json-response
 * https://github.com/square/retrofit/issues/1072#
 */

class LoggingInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.i(TAG, "inside intercept callback")
        val request = chain.request()
        val t1 = System.nanoTime()
        val url = request.url()
        var requestLog = String.format("Sending request to %s on %s%n%s",
                url, chain.connection(), request.headers())
        if (request.method().compareTo("post", ignoreCase = true) == 0) {
            requestLog = "\n" + requestLog + "\n" + bodyToString(request)
        }

        //ADD THE REQUEST HEADERS
        val headersNameValuePair = RequestUtil.getHeadersFromRequest(request)
        //ADD THE GET QUERY REQUEST PARAMETERS
        val getRequestQueryParameterNameValuePair = RequestUtil.getQueryParametersFromRequest(request)


        //Logger.d(TAG, "#REQ#POST#URL:"+ requestLog);
        Log.d(TAG, "#REQ#POST#URL:" + requestLog)
        val response = chain.proceed(request)
        val t2 = System.nanoTime()


        val responseLog = String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6, response.headers())
        val bodyStringOnly = response.body()!!.string()
        //Logger.d(TAG,"#RSP#POST#URL:" +url.toString()+" #values:\n" + bodyStringOnly);
        Log.d(TAG, "#RSP#POST#URL:" + url.toString() + " #values:\n" + bodyStringOnly)

        return response.newBuilder()
                .body(ResponseBody.create(response.body()!!.contentType(), bodyStringOnly))
                .build()
    }

    companion object {
        private val TAG = "LoggingInterceptor"


        fun bodyToString(request: Request): String {
            try {
                val copy = request.newBuilder().build()
                val buffer = Buffer()
                copy.body()!!.writeTo(buffer)
                return buffer.readUtf8()
            } catch (e: IOException) {
                return "did not work"
            }

        }
    }
}