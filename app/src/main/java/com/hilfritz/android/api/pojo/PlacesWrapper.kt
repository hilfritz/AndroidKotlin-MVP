package com.hilfritz.android.api.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Hilfritz Camallere on 16/6/17.
 *
 */
class PlacesWrapper {

    constructor() {}
    constructor(place: ArrayList<PlaceItem>) {
        this.place = place
    }

    @SerializedName("place_list")
    @Expose
    var place: ArrayList<PlaceItem>? = null

}