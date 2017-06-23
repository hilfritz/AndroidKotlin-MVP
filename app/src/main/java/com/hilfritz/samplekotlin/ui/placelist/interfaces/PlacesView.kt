package com.hilfritz.samplekotlin.ui.placelist.interfaces

import android.support.v7.widget.RecyclerView
import com.hilfritz.samplekotlin.BaseView
import com.hilfritz.samplekotlin.ui.placelist.helper.PlaceListAdapter

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

    override fun __initViews() {

    }

    fun _showList()

    fun _getAdapter():PlaceListAdapter
    fun _setAdapter(adapter:PlaceListAdapter)
    fun _getRecyclerView():RecyclerView
}