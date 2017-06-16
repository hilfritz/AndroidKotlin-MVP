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
import com.hilfritz.samplekotlin.util.ExceptionUtil
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver


/**
 * Created by Hilfritz Camallere on 24/5/17.
 */
class PlacesPresenterImpl
    constructor(var view: PlacesView, var context: Context, var savedInstanceState:Bundle?)
    : BasePresenter(), PlacesPresenterInterface {

    lateinit var apiManager:RestApiManager
    lateinit var placeListRequest: Disposable


    override fun firstInit() {
        apiManager = RestApiManager()
    }

    override fun init(context: Context, savedInstanceState: Bundle, view: BaseView?) {
        this.view = view as PlacesView
        if (isFirstTimeLoad())
            firstInit()
    }




    override fun destroy() {
        placeListRequest?.dispose()
    }

    override fun populate() {
        callPlacesApi()
    }


    override fun callPlacesApi() {
        view.showLoading()

        //CALLING THE API USING RXJAVA2


        //#1
        /*
        apiManager.getPlacesPagedSubscribable("", 0)
                .subscribe { t: PlacesWrapper? ->
                    t.let{
                        val size = t?.place?.size
                        view.hideLoading()
                        view.showFullScreenMessage("Great I found " + size + " records of places")
                    }
                }
                */
        /*
        //#2
        val value: DisposableObserver<PlacesWrapper> = object : DisposableObserver<PlacesWrapper>() {
            override fun onNext(str: PlacesWrapper) {}
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        }
        apiManager.getPlacesPagedSubscribable("",0)
                .subscribe (value)
        */

        //#3

        apiManager.getPlacesPagedSubscribable("",0)
                .subscribe (object : DisposableObserver<PlacesWrapper>() {
                    override fun onNext(placesWrapper: PlacesWrapper) {
                        placesWrapper?.let {
                            val size = placesWrapper.place?.size
                            view.hideLoading()
                            view.showFullScreenMessage("Great I found " + size + " records of places.")
                        }
                    }

                    override fun onError(e: Throwable) {
                        this.dispose()
                        if (ExceptionUtil.isNoNetworkException(e)){
                            view.hideLoading()
                            view.showFullScreenMessage("So sad, can not connect to network to get place list.")
                        }else{
                            view.hideLoading()
                            view.showFullScreenMessage("Oops, something went wrong. ["+e.localizedMessage+"]")
                        }
                    }

                    override fun onComplete() {
                        this.dispose()
                    }
                })



        /*
        val temp: DisposableObserver<String> = object : DisposableObserver<String>() {
            override fun onNext(str: String) {}
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        }
        */

    }

    private fun _getEventCompletionObserver(): DisposableObserver<String> {
        return object : DisposableObserver<String>() {
            override fun onNext(taskType: String) {
                //_log(String.format("onNext %s task", taskType))
            }

            override fun onError(e: Throwable) {
                //_log(String.format("Dang a task timeout"))
                //Timber.e(e, "Timeout Demo exception")
            }

            override fun onComplete() {
                //_log(String.format("task was completed"))
            }
        }
    }

    override fun onListItemClick(item: PlaceItem) {
        Toast.makeText(context, item.name+" is clicked", Toast.LENGTH_SHORT).show()
    }

}