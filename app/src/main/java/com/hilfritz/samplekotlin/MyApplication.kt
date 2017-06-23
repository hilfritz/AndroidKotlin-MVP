package com.hilfritz.samplekotlin

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.squareup.leakcanary.LeakCanary

/**
 * Created by Hilfritz Camallere on 16/6/17.
 *
 */
open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initLeakCanary()
        initFresco()
    }

    private fun initFresco(){
        Fresco.initialize(this)
    }
    private fun initLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not __init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app __init code...
    }

}