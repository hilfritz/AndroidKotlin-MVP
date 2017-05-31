package com.hilfritz.samplekotlin.placelist

import android.content.Context
import android.widget.Toast
import com.hilfritz.samplekotlin.api.pojo.PlaceItem

/**
 * Created by Hilfritz Camallere on 24/5/17.
 * PC name herdmacbook1
 */
class PlacesPresenterImpl constructor(var view: PlacesView, var context: Context) :PlacesPresenter{


    override fun populate() {
        callPlacesApi()
    }

    override fun init(view: PlacesView, context: Context) {

    }

    override fun callPlacesApi() {
        view.showLoading()

        //view.showFullScreenMessage("crap you")
    }

    override fun onListItemClick(item: PlaceItem) {
        Toast.makeText(context, item.name+" is clicked", Toast.LENGTH_SHORT).show()
    }

}