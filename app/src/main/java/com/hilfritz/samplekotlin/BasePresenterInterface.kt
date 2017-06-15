package com.hilfritz.samplekotlin

import android.content.Context
import android.os.Bundle

/**
 * Created by Hilfritz Camallere on 15/6/17.
 */
interface BasePresenterInterface {
    fun init(context: Context, savedInstanceState: Bundle, view:BaseView?)
    fun populate()
    fun destroy()
}