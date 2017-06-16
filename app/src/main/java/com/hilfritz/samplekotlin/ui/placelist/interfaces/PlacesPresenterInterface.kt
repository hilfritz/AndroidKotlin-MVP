package com.hilfritz.samplekotlin.ui.placelist.interfaces

/**
 * Created by Hilfritz Camallere on 24/5/17.
 */
interface PlacesPresenterInterface {
    fun _callPlacesApi()
    fun _onListItemClick(item: com.hilfritz.samplekotlin.api.pojo.PlaceItem)

}