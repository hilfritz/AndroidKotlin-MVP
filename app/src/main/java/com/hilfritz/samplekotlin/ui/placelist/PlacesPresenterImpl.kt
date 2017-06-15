package com.hilfritz.samplekotlin.placelist

import android.content.Context
import android.widget.Toast
import com.hilfritz.samplekotlin.api.pojo.PlaceItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

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
        Observable
                .just("one","two","three")
                .delay(1000, TimeUnit.MILLISECONDS)
                .throttleWithTimeout(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t: String? ->
                    view.hideLoading()
                    Toast.makeText(context, t,Toast.LENGTH_SHORT).show()

                }
                //.subscribe ({ t: String? -> Toast.makeText(context, t,Toast.LENGTH_SHORT).show() })

    }

    override fun onListItemClick(item: PlaceItem) {
        Toast.makeText(context, item.name+" is clicked", Toast.LENGTH_SHORT).show()
    }

}