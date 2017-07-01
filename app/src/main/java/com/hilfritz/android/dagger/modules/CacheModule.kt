package com.hilfritz.android.dagger.modules

import com.hilfritz.android.MyApplication
import com.hilfritz.android.util.ImageCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Hilfritz Camallere on 24/6/17.
 */
@Module(includes = arrayOf(SessionModule::class))
class CacheModule {
    @Singleton
    @Provides
    internal fun provideImageCache(myApplication: MyApplication): ImageCache {
        return ImageCache(myApplication)
    }
}
