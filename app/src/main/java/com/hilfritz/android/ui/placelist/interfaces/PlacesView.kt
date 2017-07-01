package com.hilfritz.android.ui.placelist.interfaces

import android.support.v7.widget.RecyclerView
import com.hilfritz.android.BaseView
import com.hilfritz.android.api.pojo.PlaceItem
import com.hilfritz.android.ui.placelist.helper.PlaceListAdapter
import java.util.*

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

    fun _createAdapter(list: ArrayList<PlaceItem>, presenter:PlacesPresenterInterface):PlaceListAdapter
    fun _reInitializeRecyeclerView(list: ArrayList<PlaceItem>, presenter:PlacesPresenterInterface)
    fun _notifyDataSetChangedRecyeclerView()
    fun _notifyDataSetChangedRecyeclerView(index:Int)
    fun _getRecyclerView():RecyclerView
}