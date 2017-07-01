package com.hilfritz.android.dagger.modules

import com.hilfritz.android.api.RestApiInterface
import com.hilfritz.android.api.RestApiManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Hilfritz Camallere on 24/6/17.
 *
 */
@Module
class RestApiModule {

    @Provides
    @Singleton
    fun provideRestApi(): RestApiInterface {
        return RestApiManager()
    }


}
