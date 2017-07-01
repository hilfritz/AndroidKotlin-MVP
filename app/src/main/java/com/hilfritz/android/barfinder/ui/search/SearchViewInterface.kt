package com.hilfritz.android.barfinder.ui.search

import android.support.v7.widget.RecyclerView
import com.hilfritz.android.BaseView
import com.hilfritz.android.api.pojo.PlaceItem
import com.hilfritz.android.ui.placelist.helper.PlaceListAdapter
import com.hilfritz.android.ui.placelist.interfaces.PlacesPresenterInterface
import java.util.ArrayList

/**
 * Created by home on 7/1/2017.
 */
interface SearchViewInterface :BaseView{
    fun _showList()
    fun _createAdapter(list: ArrayList<PlaceItem>, presenter: PlacesPresenterInterface): PlaceListAdapter
    fun _reInitializeRecyeclerView(list: ArrayList<PlaceItem>, presenter: PlacesPresenterInterface)
    fun _notifyDataSetChangedRecyeclerView()
    fun _notifyDataSetChangedRecyeclerView(index:Int)
    fun _getRecyclerView(): RecyclerView
}