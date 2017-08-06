package com.hilfritz.android.barfinder.ui.search

import android.content.Context
import android.os.Bundle
import com.hilfritz.android.BasePresenter
import com.hilfritz.android.BasePresenterInterface
import com.hilfritz.android.BaseView
import com.hilfritz.android.api.pojo.PlaceItem
import io.reactivex.Scheduler

/**
 * Created by home on 7/1/2017.
 */
class SearchPresenterImpl :BasePresenter(), BasePresenterInterface, SearchPresenterInterface{
    override fun _callPlacesApi() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun _onListItemClick(item: PlaceItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun _refresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun __firstInit() {
    }
    override fun __init(context: Context, savedInstanceState: Bundle, view: BaseView?, mainThread: Scheduler) {

    }

    override fun __populate() {



        super.__populate()
    }

    override fun __destroy() {
        super.__destroy()
    }
}