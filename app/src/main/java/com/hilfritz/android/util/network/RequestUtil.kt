package com.hilfritz.android.util.network

import android.util.Log
import cz.msebera.android.httpclient.NameValuePair
import cz.msebera.android.httpclient.message.BasicNameValuePair
import okhttp3.Request
import java.util.*

/**
 * Created by Hilfritz Camallere on 16/6/17.
 *
 */
object RequestUtil {
    private val TAG = "RequestUtil"
    fun getHeadersFromRequest(request: Request?): List<NameValuePair> {
        val headersNameValuePair = ArrayList<NameValuePair>()
        if (request != null) {
            val headers = request.headers()
            val names = headers!!.names()
            if (headers != null) {
                for (headerKeyName in names) {
                    val headerValue = headers.get(headerKeyName)
                    Log.d(TAG, "intercept: [query-header] $headerKeyName:$headerValue")
                    headersNameValuePair.add(BasicNameValuePair(headerKeyName, headerValue))
                }
            }
        }
        return headersNameValuePair
    }

    fun getQueryParametersFromRequest(request: Request?): List<NameValuePair> {

        val getRequestQueryParameterNameValuePair = ArrayList<NameValuePair>()
        if (request != null) {
            val url = request.url()
            //ADD THE GET QUERY REQUEST PARAMETERS
            if (url != null && url.querySize() > 0) {
                var i = 0
                val size = url.querySize()
                while (i < size) {
                    //System.out.println(url.queryParameterName(i) + ": " + url.queryParameterValue(i));
                    val key = url.queryParameterName(i)
                    val value = url.queryParameterValue(i)
                    Log.d(TAG, "intercept: [query-parameters] $key:$value")
                    getRequestQueryParameterNameValuePair.add(BasicNameValuePair(key, value))
                    i++
                }
            }
        }
        return getRequestQueryParameterNameValuePair
    }
}