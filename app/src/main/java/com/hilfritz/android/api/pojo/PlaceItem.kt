package com.hilfritz.android.api.pojo

import android.view.View
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Hilfritz Camallere on 24/5/17.
 */
class PlaceItem {

    @SerializedName("id")
    @Expose
    var id: String=""
    @SerializedName("name")
    @Expose
    var name: String=""
    @SerializedName("photo_url")
    @Expose
    var pictureUrl: String? = ""

    var isSelected = View.GONE

}
