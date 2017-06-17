package com.hilfritz.samplekotlin.ui.placelist.interfaces

import com.hilfritz.samplekotlin.BaseView

/**
 * Created by Hilfritz Camallere on 24/5/17.
 */
interface PlacesView: BaseView {
    override fun __findViews() {

    }

    override fun __showLoading() {

    }

    override fun __hideLoading() {

    }

    override fun __showFullScreenMessage(message: String) {

    }

    override fun __showDialog(tag: String, message: String) {

    }

    override fun __hideDialog(tag: String) {

    }

    override fun __init() {

    }

    fun _showList()
}