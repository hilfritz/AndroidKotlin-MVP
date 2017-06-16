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

    override fun __populate() {
        populateCounter++;
        if (populateCounter>1){
            presenterCreation = PRESENTER_CREATION.PRESENTER_REUSE;
        }
    }

    override fun __destroy(){
        populateCounter = 0;
    }

    fun __isFirstTimeLoad():Boolean {
        if (populateCounter>0){
            return false
        }
        return true
    }
}