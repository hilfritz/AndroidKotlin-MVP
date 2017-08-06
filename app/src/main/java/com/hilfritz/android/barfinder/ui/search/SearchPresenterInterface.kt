package com.hilfritz.android.barfinder.ui.search

/**
 * Created by home on 7/1/2017.
 */
interface SearchPresenterInterface {
    fun _callPlacesApi()
    fun _onListItemClick(item: com.hilfritz.android.api.pojo.PlaceItem)
    fun _refresh()
}