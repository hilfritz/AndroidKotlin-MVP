package com.hilfritz.samplekotlin.ui.placelist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.hilfritz.samplekotlin.BasePresenter
import com.hilfritz.samplekotlin.BasePresenterInterface
import com.hilfritz.samplekotlin.BaseView
import com.hilfritz.samplekotlin.api.RestApiInterface
import com.hilfritz.samplekotlin.api.RestApiManager
import com.hilfritz.samplekotlin.api.pojo.PlaceItem
import com.hilfritz.samplekotlin.api.pojo.PlacesWrapper
import com.hilfritz.samplekotlin.ui.placelist.interfaces.PlacesPresenterInterface
import com.hilfritz.samplekotlin.ui.placelist.interfaces.PlacesView
import com.hilfritz.samplekotlin.util.ExceptionUtil
import com.hilfritz.samplekotlin.util.ExceptionsUtil
import com.hilfritz.samplekotlin.util.RxJava2Util
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


/**
 * Created by Hilfritz Camallere on 24/5/17.
 */
class PlacesPresenterImpl
    constructor(var view: PlacesView, var context: Context, var savedInstanceState:Bundle?, var mainThread: Scheduler)
    : BasePresenter(), BasePresenterInterface, PlacesPresenterInterface {

    lateinit var apiManager:RestApiManager
    var placeListRequest: Disposable? = null


    override fun __firstInit() {
        apiManager = RestApiManager()
    }

    override fun __init(context: Context, savedInstanceState: Bundle, view: BaseView?) {
        this.view = view as PlacesView
        if (__isFirstTimeLoad())
            __firstInit()
    }




    override fun __destroy() {
        placeListRequest?.dispose()
    }

    override fun __populate() {
        _callPlacesApi()
    }


    override fun _callPlacesApi() {
        view.__showLoading()
        //IF PREVIOUS REQUEST IS STILL RUNNING DON'T PROCEED
        //if (RxJava2Util.isProcessing(placeListRequest))
        //    return;


        //CALLING THE API USING RXJAVA2
        //#1
        /*
        apiManager.getPlacesPagedSubscribable("", 0)
                .subscribe { t: PlacesWrapper? ->
                    t.let{
                        val size = t?.place?.size
                        view.__hideLoading()
                        view.__showFullScreenMessage("Great I found " + size + " records of places")
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

        apiManager.getPlacesPagedObservable("",0)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread)
                .subscribe (object : DisposableObserver<PlacesWrapper>() {
                    override fun onNext(placesWrapper: PlacesWrapper) {
                        placesWrapper?.let {
                            val size = placesWrapper.place?.size
                            view.__hideLoading()
                            view._showList()
                            System.out.printf("Great I found " + size + " records of places.")
                            view.__showFullScreenMessage("Great I found " + size + " records of places.")
                        }
                        System.out.printf("onNext()")
                    }

                    override fun onError(e: Throwable) {
                        if (ExceptionsUtil.isNoNetworkException(e)){
                            view.__hideLoading()
                            view.__showFullScreenMessage("So sad, can not connect to network to get place list.")
                        }else{
                            view.__hideLoading()
                            view.__showFullScreenMessage("Oops, something went wrong. ["+e.localizedMessage+"]")
                        }
                        System.console().printf("onError()")
                        this.dispose()
                    }

                    override fun onComplete() {
                        this.dispose()
                        //System.out.printf("onComplete()")
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

    override fun _onListItemClick(item: PlaceItem) {
        Toast.makeText(context, item.name+" is clicked", Toast.LENGTH_SHORT).show()
    }

}