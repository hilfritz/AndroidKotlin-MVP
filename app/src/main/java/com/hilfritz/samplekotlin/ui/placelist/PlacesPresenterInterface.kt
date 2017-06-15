package com.hilfritz.samplekotlin.ui.placelist

import com.hilfritz.samplekotlin.api.pojo.PlaceItem

/**
 * Created by Hilfritz Camallere on 24/5/17.
 */
interface PlacesPresenter {
    fun callPlacesApi()
    fun onListItemClick(item: PlaceItem)

}