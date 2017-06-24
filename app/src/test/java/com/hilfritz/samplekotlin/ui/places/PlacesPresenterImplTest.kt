package com.hilfritz.samplekotlin.ui.places

import android.os.Bundle
import com.hilfritz.samplekotlin.AndroidTest
import com.hilfritz.samplekotlin.api.RestApiInterface
import com.hilfritz.samplekotlin.api.RestApiManager
import com.hilfritz.samplekotlin.api.pojo.PlaceItem
import com.hilfritz.samplekotlin.api.pojo.PlacesWrapper
import com.hilfritz.samplekotlin.ui.placelist.PlacesPresenterImpl
import com.hilfritz.samplekotlin.ui.placelist.interfaces.PlacesView
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Hilfritz Camallere on 16/6/17.
 *
 */
class PlacesPresenterImplTest : AndroidTest(){

    lateinit var presenter:PlacesPresenterImpl
    lateinit var view:PlacesView
    lateinit var apiManager:RestApiInterface


    val EXCEPTION_MESSAGE1 = "SORRY"

    val MANY_PLACES = Arrays.asList(PlaceItem(), PlaceItem());
    val manyPlacesWrapper = PlacesWrapper(MANY_PLACES as ArrayList<PlaceItem>)
    val emptyPlacesWrapper = PlacesWrapper(ArrayList<PlaceItem>())

    @After
    fun clear(){
        RxJavaPlugins.reset()
    }
    @Before
    fun init(){
        //MOCKS THE subscribeOn(Schedulers.io()) to use the same thread the test is being run on
        //Schedulers.trampoline() runs the test in the same thread used by the test
        RxJavaPlugins.setIoSchedulerHandler { _ -> Schedulers.trampoline() }

        view = mock<PlacesView>()
        apiManager = mock<RestApiManager>()
        presenter = PlacesPresenterImpl(view,context(), Bundle(), Schedulers.trampoline())
        presenter.apiManager = apiManager

        //exceptionPlacesWrapper = throw Exception(EXCEPTION_MESSAGE1);
    }


    @Test
    fun loadAllPlacesTest() {
        whenever(apiManager.getPlacesPagedObservable(anyString(), anyInt())).thenReturn(Observable.just(manyPlacesWrapper))

        presenter.__populate()
        verify(view, atLeastOnce()).__showLoading()
        verify(view, atLeastOnce())._showList()
        verify(view).__hideLoading()
        verify(view).__showFullScreenMessage(anyString())
    }

    @Test
    fun loadEmptyPlacesTest() {

        whenever(apiManager.getPlacesPagedObservable(anyString(), anyInt())).thenReturn(Observable.just(emptyPlacesWrapper))
        presenter.__populate()
        verify(view, atLeastOnce()).__showLoading()
        verify(view, atLeastOnce())._showList()
        verify(view).__hideLoading()
        verify(view).__showFullScreenMessage(anyString())
    }

    @Test
    fun loadExceptionPlacesTest() {
        whenever(apiManager.getPlacesPagedObservable(anyString(), anyInt())).thenReturn(Observable.error(Exception(EXCEPTION_MESSAGE1)))
        presenter.__populate()
        verify(view, atLeastOnce()).__showLoading()
        verify(view, never())._showList()
        verify(view).__hideLoading()
        verify(view).__showFullScreenMessage(anyString())

        //CHECK THE PARAMETER OF __showFullScreenMessage IF IT REALLY HAS THE EXCEPTION_MESSAGE1
        //val fooCaptor = ArgumentCaptor.forClass<String, String>(String::class.java)

        //verify(view).__showFullScreenMessage(fooCaptor.capture())
            //getValue() contains value set in second call to doSomething()
        //assertTrue(fooCaptor.value.toString().contains(EXCEPTION_MESSAGE1));


    }

    /*
    @Test
    fun loadPlacesClickTest() {

        whenever(apiManager.getPlacesPagedObservable(anyString(), anyInt())).thenReturn(Observable.just(manyPlacesWrapper))
        presenter.__firstInit()
        presenter.__init(context(),Bundle(),view)
        presenter.__populate()
        verify(view, atLeastOnce()).__showLoading()
        verify(view, atLeastOnce())._showList()
        verify(view).__hideLoading()

        //view._getRecyclerView().click

    }
    */


}