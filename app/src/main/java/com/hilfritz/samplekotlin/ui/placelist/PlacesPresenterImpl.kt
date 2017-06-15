package com.hilfritz.samplekotlin.ui.placelist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.hilfritz.samplekotlin.BasePresenter
import com.hilfritz.samplekotlin.BaseView
import com.hilfritz.samplekotlin.api.pojo.PlaceItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Hilfritz Camallere on 24/5/17.
 */
class PlacesPresenterImpl
    constructor(var view: PlacesView, var context: Context, var savedInstanceState:Bundle?)
    : BasePresenter(), PlacesPresenterInterface {


    override fun init(context: Context, savedInstanceState: Bundle, view: BaseView?) {

    }

    override fun destroy() {

    }

    override fun populate() {
        callPlacesApi()
    }


    override fun callPlacesApi() {
        view.showLoading()

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