package com.hilfritz.android.ui.placelist.interfaces

/**
 * Created by Hilfritz Camallere on 24/5/17.
 */
interface PlacesPresenterInterface {
    fun _callPlacesApi()
    fun _onListItemClick(item: com.hilfritz.android.api.pojo.PlaceItem)
    fun _refresh()
}