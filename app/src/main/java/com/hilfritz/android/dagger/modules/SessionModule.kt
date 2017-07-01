package com.hilfritz.samplekotlin.dagger.modules

import com.hilfritz.samplekotlin.MyApplication
import com.hilfritz.samplekotlin.dagger.session.SessionData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Hilfritz Camallere on 24/6/17.
 *
 */
@Module
class SessionModule(internal var myApplication: MyApplication) {

    @Singleton
    @Provides
    internal fun provideSessionData(): SessionData {
        return SessionData(this.myApplication)
    }

}