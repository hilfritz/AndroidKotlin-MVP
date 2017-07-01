package com.hilfritz.android.ui.basic

import android.os.Bundle
import com.hilfritz.android.AndroidTest
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
        Assert.assertTrue(presenter.__isFirstTimeLoad())
        presenter.__populate()

        Assert.assertTrue(presenter.__isFirstTimeLoad()==false)
        presenter.__destroy()
        Assert.assertTrue(presenter.__isFirstTimeLoad())

        presenter.__populate()
        Assert.assertTrue(presenter.__isFirstTimeLoad()==false)
        presenter.__populate()
        presenter.__populate()
        presenter.__populate()

        Assert.assertTrue(presenter.__isFirstTimeLoad()==false)
    }
}