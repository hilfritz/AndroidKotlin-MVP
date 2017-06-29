package com.hilfritz.samplekotlin.ui.basic

import android.content.Context
import android.os.Bundle
import com.hilfritz.samplekotlin.BasePresenter
import com.hilfritz.samplekotlin.BaseView
import io.reactivex.Scheduler

/**
 * Created by Hilfritz Camallere on 15/6/17.
 *
 */
class BasicPresenterImpl
constructor(var view: BasicPresenterView, var context: Context, var savedInstanceState: Bundle)
    : BasePresenter(), BasicPresenterInterface
{
    override fun __init(context: Context, savedInstanceState: Bundle, view: BaseView?, mainThread: Scheduler) {
    }

    override fun __firstInit() {
    }

    override fun __populate() {
        super.__populate()
    }

    override fun __destroy() {
        super.__destroy()
    }
}