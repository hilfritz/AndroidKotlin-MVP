package com.hilfritz.samplekotlin.ui.basic

import android.os.Bundle
import com.hilfritz.samplekotlin.AndroidTest
import org.junit.Assert
import org.junit.Test

/**
 * Created by Hilfritz Camallere on 15/6/17.
 *
 */
class BasicPresenterImplTest : AndroidTest(){

    lateinit var presenter: BasicPresenterImpl
    lateinit var view: BasicPresenterView




    fun isFirstLoadTestInit(){
        class BasicPresenterView1: BasicPresenterView
        view = BasicPresenterView1();
        presenter = BasicPresenterImpl(view, context(), Bundle())
    }


    @Test
    fun isFirstLoadTest(){
        isFirstLoadTestInit()
        Assert.assertTrue(presenter.isFirstTimeLoad())
        presenter.populate()

        Assert.assertTrue(presenter.isFirstTimeLoad()==false)
        presenter.destroy()
        Assert.assertTrue(presenter.isFirstTimeLoad())

        presenter.populate()
        Assert.assertTrue(presenter.isFirstTimeLoad()==false)
        presenter.populate()
        presenter.populate()
        presenter.populate()

        Assert.assertTrue(presenter.isFirstTimeLoad()==false)
    }
}