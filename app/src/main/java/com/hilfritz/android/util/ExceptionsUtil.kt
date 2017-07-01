package com.hilfritz.android.util

import java.net.UnknownHostException

/**
 * Created by home on 6/18/2017.
 */
object ExceptionsUtil {

    fun isNoNetworkException(e: Throwable?): Boolean {
        if (e != null) {
            if (e is UnknownHostException) {
                return true
            }
        }
        return false
    }
}
