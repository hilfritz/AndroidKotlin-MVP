package com.hilfritz.samplekotlin.ui.placelist

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hilfritz.samplekotlin.BasePresenter
import com.hilfritz.samplekotlin.BasePresenterInterface
import com.hilfritz.samplekotlin.BaseView
import com.hilfritz.samplekotlin.api.RestApiInterface
import com.hilfritz.samplekotlin.api.pojo.PlaceItem
import com.hilfritz.samplekotlin.api.pojo.PlacesWrapper
import com.hilfritz.samplekotlin.ui.placelist.helper.PlaceListAdapter
import com.hilfritz.samplekotlin.ui.placelist.interfaces.PlacesPresenterInterface
import com.hilfritz.samplekotlin.ui.placelist.interfaces.PlacesView
import com.hilfritz.samplekotlin.util.ExceptionsUtil
import com.hilfritz.samplekotlin.util.RxJava2Util
import com.hilfritz.samplekotlin.util.log.Logger
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by Hilfritz Camallere on 24/5/17.
 */
class PlacesPresenterImpl()
    : BasePresenter(), BasePresenterInterface, PlacesPresenterInterface {

    override var TAG = "PlacesPresenterImpl"
    lateinit var apiManager:RestApiInterface
    var placeListRequest: Disposable? = null
    var list:ArrayList<PlaceItem> = ArrayList<PlaceItem>();
    lateinit var mainThread:Scheduler
    lateinit var view:PlacesView
    var isFromRotation=false



    override fun __firstInit() {

    }

    override fun __init(context: Context, savedInstanceState: Bundle, view: BaseView?, mainThread: Scheduler) {
        this.view = view as PlacesView
        if (__isFirstTimeLoad())
            __firstInit()

        this.mainThread = mainThread
        view._setAdapter(PlaceListAdapter(list, this))
        view._getAdapter().notifyDataSetChanged()
    }

    override fun _refresh() {
        //Toast.makeText(view.__getActivity(), "refreshing", Toast.LENGTH_SHORT).show()
        __setForRefresh()
        __populate()
    }


    override fun __destroy() {
        super.__destroy()
        placeListRequest?.dispose()

        if((view.__getActivity()).isFinishing()){
            //Log.i("DEBUG", "App will Terminate ");
        }else{
            //Log.i("DEBUG", "Orientation changed");
            isFromRotation = true
        }
        this.view = null!!
    }

    override fun __populate() {

        _callPlacesApi()
        isFromRotation = false
        super.__populate()
    }


    override fun _callPlacesApi() {
        view.__showLoading()
        //IF PREVIOUS REQUEST IS STILL RUNNING DON'T PROCEED
        if (RxJava2Util.isProcessing(placeListRequest)){
            Logger.d(TAG,"_callPlacesApi() still processings")
            return
        }

        if (__isFirstTimeLoad()==false && list.size>0){
            Logger.d(TAG,"_callPlacesApi() list already downloaded and rotation happened")
            isFromRotation = false
            view.__hideLoading()
            Toast.makeText(view.__getActivity(), "already loaded", Toast.LENGTH_SHORT).show()
            return
        }



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
                .delay(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread)
                .subscribe (object : DisposableObserver<PlacesWrapper>() {
                    override fun onNext(placesWrapper: PlacesWrapper) {
                        placesWrapper?.let {
                            val size = placesWrapper.place?.size
                            if (size!!>0){
                                list.clear()
                            }
                            list.addAll(ArrayList(placesWrapper.place)) //NEED TO CONVERT TO ARRAYLIST
                            view._getAdapter().notifyDataSetChanged()
                            view.__hideLoading()
                            view._showList()
                            System.out.printf("Great I found " + size + " records of places.")
                            //view.__showFullScreenMessage("Great I found " + size + " records of places.")
                        }
                        System.out.println("onNext()")
                    }

                    override fun onError(e: Throwable) {
                        if (ExceptionsUtil.isNoNetworkException(e)){
                            view.__hideLoading()
                            view.__showFullScreenMessage("So sad, can not connect to network to get place list.")
                        }else{
                            view.__hideLoading()
                            view.__showFullScreenMessage("Oops, something went wrong. ["+e.localizedMessage+"]")
                        }
                        System.out.println("onError()")
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

    override fun _onListItemClick(place: PlaceItem) {
        var temp=place
        val index = list.indexOf(temp)
        if (place.isSelected == View.GONE){
            place.isSelected = View.VISIBLE
        }else if (place.isSelected == View.VISIBLE){
            place.isSelected = View.GONE
        }

        //place.set__viewIsSelected(newVisibility)
        //Timber.d("onListItemClick: index:"+index);
        //view._getAdapter().notifyDataSetChanged()
        view._getAdapter().notifyItemChanged(index)
        Logger.d(TAG, "_onListItemClick:"+index+" "+temp.name+" "+list[index].isSelected)
    }



}