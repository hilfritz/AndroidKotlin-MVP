package com.hilfritz.android.dagger.modules

import com.hilfritz.android.MyApplication
import com.hilfritz.android.ui.placelist.PlacesPresenterImpl
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