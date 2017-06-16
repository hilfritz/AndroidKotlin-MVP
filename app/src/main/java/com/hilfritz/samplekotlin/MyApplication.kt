package com.hilfritz.samplekotlin

import android.app.Application
import com.squareup.leakcanary.LeakCanary

/**
 * Created by Hilfritz Camallere on 16/6/17.
 *
 */
open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initLeakCanary()
    }
    private fun initLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }

}