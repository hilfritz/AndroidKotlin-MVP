package com.hilfritz.samplekotlin.ui.placelist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.hilfritz.samplekotlin.BasePresenter
import com.hilfritz.samplekotlin.BaseView
import com.hilfritz.samplekotlin.api.RestApiManager
import com.hilfritz.samplekotlin.api.pojo.PlaceItem
import com.hilfritz.samplekotlin.api.pojo.PlacesWrapper
import com.hilfritz.samplekotlin.ui.placelist.interfaces.PlacesPresenterInterface
import com.hilfritz.samplekotlin.ui.placelist.interfaces.PlacesView

/**
 * Created by Hilfritz Camallere on 24/5/17.
 */
class PlacesPresenterImpl
    constructor(var view: PlacesView, var context: Context, var savedInstanceState:Bundle?)
    : BasePresenter(), PlacesPresenterInterface {

    lateinit var apiManager:RestApiManager

    override fun firstInit() {
        apiManager = RestApiManager()
    }

    override fun init(context: Context, savedInstanceState: Bundle, view: BaseView?) {
        this.view = view as PlacesView
        if (isFirstTimeLoad())
            firstInit()
    }




    override fun destroy() {

    }

    override fun populate() {
        callPlacesApi()
    }


    override fun callPlacesApi() {
        view.showLoading()

        /*
        val subscriber = object : Subscriber<PlacesWrapper>() {
            fun onCompleted() {
                //Timber.d("callPlaceListApi: onCompleted: ")
                view.hideLoading()
            }

            fun onError(e: Throwable) {
                //Timber.d("callPlaceListApi: onError: ")
                view.hideLoading()
                e.printStackTrace()
                if (ExceptionUtil.isNoNetworkException(e)) {
                    Toast.makeText(context, "No Internet connection", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context), e.message, Toast.LENGTH_SHORT).show()
                }
            }

            fun onNext(placesWrapper: PlacesWrapper?) {
                //Timber.d("callPlaceListApi: onNext: ")
                if (placesWrapper != null) {
                    if (placesWrapper.getPlace().size() > 0) {
                        getPlaceList().clear()
                        getPlaceList().addAll(placesWrapper.getPlace())
                        view.getAdapter().notifyDataSetChanged()
                    }
                }
            }
        }
        */


        apiManager.getPlacesPagedSubscribable("", 0)
                .subscribe { t: PlacesWrapper? ->
                    if (t != null) {

                        val size = t.place?.size
                        //Logger.d("", size.toString()+"<<")
                        //Toast.makeText(context, ""+ size+" records found",Toast.LENGTH_SHORT).show()
                        view.hideLoading()
                        view.showFullScreenMessage("Great I found "+ size+" records of places")
                    }
                }

        /*

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
                */
    }

    override fun onListItemClick(item: PlaceItem) {
        Toast.makeText(context, item.name+" is clicked", Toast.LENGTH_SHORT).show()
    }

}