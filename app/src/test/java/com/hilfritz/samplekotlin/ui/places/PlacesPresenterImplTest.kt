package com.hilfritz.samplekotlin.ui.places

import android.os.Bundle
import com.hilfritz.samplekotlin.AndroidTest
import com.hilfritz.samplekotlin.api.RestApiManager
import com.hilfritz.samplekotlin.api.pojo.PlaceItem
import com.hilfritz.samplekotlin.api.pojo.PlacesWrapper
import com.hilfritz.samplekotlin.ui.placelist.PlacesPresenterImpl
import com.hilfritz.samplekotlin.ui.placelist.interfaces.PlacesView
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.*


/**
 * Created by Hilfritz Camallere on 16/6/17.
 *
 */
class PlacesPresenterImplTest : AndroidTest(){

    lateinit var presenter:PlacesPresenterImpl
    lateinit var view:PlacesView
    lateinit var apiManager:RestApiManager
    //lateinit var apiManager:RestApiManager

    val MANY_PLACES = Arrays.asList(PlaceItem(), PlaceItem());
    val manyPlacesWrapper = PlacesWrapper(MANY_PLACES)
    val emptyPlacesWrapper = PlacesWrapper(Collections.emptyList())

    @After
    fun clear(){
        RxJavaPlugins.reset()
    }
    @Before
    fun init(){
        //MOCKS THE subscribeOn(Schedulers.io()) to use the same thread the test is being run on
        //Schedulers.trampoline() runs the test in the same thread used by the test
        RxJavaPlugins.setIoSchedulerHandler { t -> Schedulers.trampoline() }

        view = Mockito.mock<PlacesView>(PlacesView::class.java)
        apiManager = Mockito.mock(RestApiManager::class.java)
        presenter = PlacesPresenterImpl(view,context(), Bundle(), Schedulers.trampoline())
        presenter.apiManager = apiManager
    }


    @Test
    fun loadAllPlacesTest() {
        Mockito.`when`(apiManager.getPlacesPagedObservable(Mockito.anyString(), Mockito.anyInt())).thenReturn(Observable.just(manyPlacesWrapper))

        presenter.__populate()
        Mockito.verify(view, Mockito.atLeastOnce()).__showLoading()
        Mockito.verify(view, Mockito.atLeastOnce())._showList()
        Mockito.verify(view).__hideLoading()
        Mockito.verify(view).__showFullScreenMessage(Mockito.anyString())
    }

    @Test
    fun loadEmptyPlacesTest() {

        Mockito.`when`(apiManager.getPlacesPagedObservable(Mockito.anyString(), Mockito.anyInt())).thenReturn(Observable.just(emptyPlacesWrapper))
        presenter.__populate()
        Mockito.verify(view, Mockito.atLeastOnce()).__showLoading()
        Mockito.verify(view, Mockito.atLeastOnce())._showList()
        Mockito.verify(view).__hideLoading()
        Mockito.verify(view).__showFullScreenMessage(Mockito.anyString())
    }

    //@Test
    fun loadExceptionPlacesTest() {

        Mockito.`when`(apiManager.getPlacesPagedObservable(Mockito.anyString(), Mockito.anyInt())).thenThrow(RuntimeException("sorry"))
        presenter.__populate()
        Mockito.verify(view, Mockito.atLeastOnce()).__showLoading()
        Mockito.verify(view, Mockito.atLeastOnce())._showList()
        Mockito.verify(view).__hideLoading()
        Mockito.verify(view).__showFullScreenMessage("sorry")
    }

}