package com.hilfritz.android

/**
 * Created by Hilfritz Camallere on 24/5/17.
 */
interface BaseView {
    fun __findViews()
    fun __showLoading()
    fun __hideLoading()
    fun __showFullScreenMessage(message: String)
    fun __showDialog(tag:String, message: String)
    fun __hideDialog(tag:String)
    fun __initViews()
    fun __getActivity():BaseActivity
    fun __isFinishing():Boolean
}