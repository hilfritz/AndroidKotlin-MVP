package com.hilfritz.samplekotlin

/**
 * Created by Hilfritz Camallere on 24/5/17.
 */
interface BaseView {
    fun findViews()
    fun showLoading()
    fun hideLoading()
    fun showFullScreenMessage(message: String)
    fun showDialog(tag:String, message: String)
    fun hideDialog(tag:String)
    fun init()
}