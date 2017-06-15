package com.hilfritz.samplekotlin.placelist

import com.hilfritz.samplekotlin.BaseView

/**
 * Created by Hilfritz Camallere on 24/5/17.
 * PC name herdmacbook1
 */
interface PlacesView:BaseView {
    fun showFullScreenMessage(message: String)
    fun showList()
    fun showDialog(message: String)
}