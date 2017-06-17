package com.hilfritz.samplekotlin.ui.places

import com.hilfritz.samplekotlin.AndroidTest
import com.hilfritz.samplekotlin.api.RestApiInterface
import com.hilfritz.samplekotlin.api.RestApiManager
import com.hilfritz.samplekotlin.api.pojo.PlaceItem
import com.hilfritz.samplekotlin.api.pojo.PlacesWrapper
import com.hilfritz.samplekotlin.ui.placelist.PlacesPresenterImpl
import com.hilfritz.samplekotlin.ui.placelist.interfaces.PlacesView
import io.reactivex.Observable
import io.reactivex.Observable.just
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.util.*


/**
 * Created by Hilfritz Camallere on 16/6/17.
 *
 */
class PlacesPresenterImplTest : AndroidTest(){

    lateinit var presenter:PlacesPresenterImpl
    lateinit var view:PlacesView
    lateinit var apiManager:RestApiManager

    val MANY_PLACES = Arrays.asList(PlaceItem(), PlaceItem());
    var placesWrapper = PlacesWrapper()


    fun loadAllPlacesTestInit(){
        placesWrapper.place = MANY_PLACES
        view = Mockito.mock<PlacesView>(PlacesView::class.java)
        //apiManager = Mockito.mock<RestApiManager>(RestApiManager::class.java)
        var apiManager2 = Mockito.mock(RestApiManager::class.java)
        //`when`(apiManager.getPlacesPagedSubscribable(Mockito.anyString(), Mockito.anyInt())).thenReturn(Observable.just(placesWrapper))
        Mockito.`when`(apiManager2.getPlacesPagedSubscribable(Mockito.anyString(), Mockito.anyInt())).thenReturn(Observable.just(placesWrapper))
        presenter = PlacesPresenterImpl(view,context(),null)
        presenter.apiManager = apiManager2;

        //presenter.apiManager.api = mock(RestApiInterface::class.java)

        //`when`(presenter.apiManager.api.getPlacesPagedObservable(anyString(), anyInt())).thenReturn(Observable.just(placesWrapper))


    }
    @Test
    fun loadAllPlacesTest() {
        loadAllPlacesTestInit()

        presenter.__populate()

        Mockito.verify(view, Mockito.atLeastOnce()).__showLoading()
        //Mockito.verify(view, Mockito.atLeastOnce())._showList()
        //verify(view).__hideLoading()
        //Mockito.verify(view, Mockito.atLeastOnce()).__hideLoading()
        //verify(view).__showFullScreenMessage(anyString())
    }

}