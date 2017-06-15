package com.hilfritz.samplekotlin.ui.basic

import android.content.Context
import android.os.Bundle
import com.hilfritz.samplekotlin.BasePresenter
import com.hilfritz.samplekotlin.BaseView

/**
 * Created by Hilfritz Camallere on 15/6/17.
 *
 */
class BasicPresenterImpl
constructor(var view: BasicPresenterView, var context: Context, var savedInstanceState: Bundle)
    : BasePresenter(), BasicPresenterInterface
{
    override fun init(context: Context, savedInstanceState: Bundle, view: BaseView?) {
    }

    override fun populate() {
        super.populate()
    }

    override fun destroy() {
        super.destroy()
    }
}