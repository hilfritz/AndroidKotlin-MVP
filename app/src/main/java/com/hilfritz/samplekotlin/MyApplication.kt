package com.hilfritz.samplekotlin

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.hilfritz.samplekotlin.dagger.components.AppComponent
import com.hilfritz.samplekotlin.dagger.components.DaggerAppComponent
import com.hilfritz.samplekotlin.dagger.modules.*
import com.squareup.leakcanary.LeakCanary

/**
 * Created by Hilfritz Camallere on 16/6/17.
 *
 */
open class MyApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        initLeakCanary()
        initFresco()
    }

    private fun initFresco(){
        Fresco.initialize(this)
    }
    private fun initLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not __initViews your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app __initViews code...
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .restApiModule(RestApiModule())
                .presenterModule(PresenterModule(this))
                .sessionModule(SessionModule(this))
                .utilityModule(UtilityModule())
                .cacheModule(CacheModule())
                .build()

    }

}