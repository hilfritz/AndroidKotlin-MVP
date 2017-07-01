package com.hilfritz.android.dagger.session

import com.hilfritz.android.MyApplication
import java.util.*

/**
 * Created by Hilfritz Camallere on 24/6/17.
 *
 */
class SessionData(var myApplication: MyApplication) {
    var sessionUuid: String = ""

    fun sessionStart() {
        sessionUuid = UUID.randomUUID().toString() + "<>" + System.currentTimeMillis()
    }

    fun sessionEnd() {
        sessionUuid = ""
    }

}