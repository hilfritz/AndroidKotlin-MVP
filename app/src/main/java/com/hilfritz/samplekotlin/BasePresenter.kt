package com.hilfritz.samplekotlin

import android.util.Log

/**
 * Created by Hilfritz Camallere on 15/6/17.
 */
abstract class BasePresenter: BasePresenterInterface{
    open var TAG = "BasePresenter"
    private var populateCounter = 0
    var presenterCreation = PRESENTER_CREATION.PRESENTER_NEW

    enum class PRESENTER_CREATION{
        PRESENTER_NEW,
        PRESENTER_REUSE
    }

    override fun __populate() {
        Log.d(TAG, "populate")
        populateCounter++;
        if (populateCounter>1){
            presenterCreation = PRESENTER_CREATION.PRESENTER_REUSE;
        }
    }

    override fun __destroy(){
        Log.d(TAG, "__destroy")
        populateCounter = 0
    }
    fun __setForRefresh(){
        populateCounter = 0
    }

    fun __isFirstTimeLoad():Boolean {
        if (populateCounter>0){
            return false
        }
        return true
    }
}