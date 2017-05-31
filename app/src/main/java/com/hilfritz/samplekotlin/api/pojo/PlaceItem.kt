package com.hilfritz.samplekotlin.api.pojo

import android.view.View

/**
 * Created by Hilfritz Camallere on 24/5/17.
 * PC name herdmacbook1
 */
class PlaceItem (val id:Int=0 ,val name:String="",val pictureUrl:String="",val isSelected:Int=PlaceItem.SELECTION.NOT_SELECTED){

    companion object SELECTION {
        val IS_SELECTED = 1;
        val NOT_SELECTED = 0;
    }

    /*
    var id:Int=0;
    var name:String="";
    var pictureUrl:String="";
    var isSelected:Int=NOT_SELECTED;
    */
    fun viewVisibility():Int{
        if (isSelected== IS_SELECTED)
            return View.VISIBLE
        if (isSelected== NOT_SELECTED)
            return View.GONE
        return View.GONE
    }
}