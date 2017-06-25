package com.hilfritz.samplekotlin.dagger.components

import com.hilfritz.samplekotlin.dagger.modules.*
import com.hilfritz.samplekotlin.ui.placelist.PlacesPresenterImpl
import com.hilfritz.samplekotlin.ui.placelist.view.PlacesFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Hilfritz Camallere on 24/6/17.
 *
 */
@Singleton
@Component(modules = arrayOf(RestApiModule::class, PresenterModule::class, UtilityModule::class, SessionModule::class, CacheModule::class))
interface AppComponent {
    fun inject(fragment: PlacesFragment)
    fun inject(presenter: PlacesPresenterImpl)
}
