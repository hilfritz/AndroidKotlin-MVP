package com.hilfritz.samplekotlin.dagger.modules

import com.hilfritz.samplekotlin.MyApplication
import com.hilfritz.samplekotlin.ui.placelist.PlacesPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Hilfritz Camallere on 24/6/17.
 *
 */
@Module
class PresenterModule(private val myApplication: MyApplication) {

    //FRAGMENTS HERE
    @Provides
    @Singleton
    fun providePlaceListPresenter(): PlacesPresenterImpl {
        return PlacesPresenterImpl()
    }

    //ACTIVITY HERE

}