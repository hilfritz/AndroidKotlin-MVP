package com.hilfritz.samplekotlin.placelist

import android.content.Context
import com.hilfritz.samplekotlin.api.pojo.PlaceItem

/**
 * Created by Hilfritz Camallere on 24/5/17.
 * PC name herdmacbook1
 */
interface PlacesPresenter{

    /*
    fun sum(x: Int=0, y:Int): Int {
        return x+y
    }
    fun sum2(x: Int=0, y:Int): Int = x+y
    fun sum3(x: Int=0, y:Int) = x+y
    */

    fun populate()
    fun init(view: PlacesView, context:Context)
    fun callPlacesApi()
    fun onListItemClick(item: PlaceItem)

}