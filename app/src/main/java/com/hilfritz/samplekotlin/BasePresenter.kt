package com.hilfritz.samplekotlin

/**
 * Created by Hilfritz Camallere on 15/6/17.
 */
abstract class BasePresenter: BasePresenterInterface{
    private var populateCounter = 0;
    var presenterCreation = PRESENTER_CREATION.PRESENTER_NEW

    enum class PRESENTER_CREATION{
        PRESENTER_NEW,
        PRESENTER_REUSE
    }
    override fun populate() {
        populateCounter++;
        if (populateCounter>1){
            presenterCreation = PRESENTER_CREATION.PRESENTER_REUSE;
        }
    }



    override fun destroy(){
        populateCounter = 0;
    }

    fun isFirstTimeLoad():Boolean {
        if (populateCounter==1){
            return true
        }
        return false
    }
}