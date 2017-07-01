package com.hilfritz.android.dagger.modules

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Hilfritz Camallere on 24/6/17.
 *
 */
@Module
class UtilityModule {
    @Provides
    @Singleton
    internal fun providesGson(): Gson {
        return Gson()
    }

}