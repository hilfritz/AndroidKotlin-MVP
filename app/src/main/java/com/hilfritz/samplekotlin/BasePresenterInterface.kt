package com.hilfritz.samplekotlin

import android.content.Context
import android.os.Bundle
import io.reactivex.Scheduler

/**
 * Created by Hilfritz Camallere on 15/6/17.
 */
interface BasePresenterInterface {
    fun __firstInit()
    fun __init(context: Context, savedInstanceState: Bundle, view:BaseView?, mainThread: Scheduler)
    fun __populate()
    fun __destroy()
}