package com.hilfritz.samplekotlin

import android.content.Context
import android.os.Bundle

/**
 * Created by Hilfritz Camallere on 15/6/17.
 */
interface BasePresenterInterface {
    fun __firstInit()
    fun __init(context: Context, savedInstanceState: Bundle, view:BaseView?)
    fun __populate()
    fun __destroy()
}