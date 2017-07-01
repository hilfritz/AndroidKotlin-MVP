package com.hilfritz.android.barfinder.repo.places

import com.hilfritz.android.barfinder.repo.places.pojo.PlaceBean

/**
 * Created by home on 7/1/2017.
 */
interface PlacesRepo {
    fun init()
    fun search(str:String):ArrayList<PlaceBean>
    fun nearby(latitude:Long, longitude:Long):ArrayList<PlaceBean>
}