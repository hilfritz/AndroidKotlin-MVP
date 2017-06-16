package com.hilfritz.samplekotlin.ui.placelist.interfaces

import com.hilfritz.samplekotlin.api.pojo.PlaceItem

/**
 * Created by Hilfritz Camallere on 24/5/17.
 */
interface PlacesPresenterInterface {
    fun callPlacesApi()
    fun onListItemClick(item: com.hilfritz.samplekotlin.api.pojo.PlaceItem)

}