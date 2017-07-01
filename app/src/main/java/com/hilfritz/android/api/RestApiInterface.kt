package com.hilfritz.android.api

import com.hilfritz.android.api.pojo.PlacesWrapper
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Created by Hilfritz Camallere on 16/6/17.
 *
 */
interface RestApiInterface {

    @GET(RestApiManager.PLACES_URL)
    fun getPlacesObservable(): Observable<PlacesWrapper>

    @GET(RestApiManager.PLACES_URL)
    fun getPlacesPagedResponseObservable(
            @Header("header_access_token") accessToken: String,
            @Query("page") page: Int
    ): Observable<Response<PlacesWrapper>>

    @GET(RestApiManager.PLACES_URL)
    fun getPlacesPagedObservable(
            @Header("header_access_token") accessToken: String?,
            @Query("page") page: Int?
    ): Observable<PlacesWrapper>


    //@GET(RestApiManager.PLACES_URL)
    //abstract fun getPlacesCall(): Call<PlacesWrapper>
}