package com.hilfritz.samplekotlin.api

import com.hilfritz.samplekotlin.api.pojo.PlaceItem
import com.hilfritz.samplekotlin.api.pojo.PlacesWrapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Hilfritz Camallere on 16/6/17.
 * see https://guides.codepath.com/android/Consuming-APIs-with-Retrofit
 */

open class RestApiManager {

    var api: RestApiInterface
        internal set
    internal var retrofit: Retrofit

    init {
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = okhttp3.OkHttpClient().newBuilder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(LoggingInterceptor())   //<<<----- EXCLUSIVE ONLY FOR THIS CLASS,
                .build()

        //RESTAPIMANAGER.JAVA SHOULD NOT BE USED WHEN DOWNLOADING FILES, YOU SHOULD CREATE ANOTHER MANAGER CLASS
        //THE OKHTTPCLIENT OF THAT OTHER MANAGER CLASS SHOULD NOT HAVE LoggingInterceptor() set as interceptor

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//very important for RXJAVA and retrofit
                .build()
        api = retrofit.create(RestApiInterface::class.java)

    }

    val placesSubscribable: Observable<PlacesWrapper>
        get() = api.getPlacesObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .delay(5000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())

    fun getPlacesPagedResponseSubscribable(accessToken: String, page: Int
        ): Observable<Response<PlacesWrapper>> {
            return api.getPlacesPagedResponseObservable(
                    accessToken,
                    page
            ).subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
    }

    fun getPlacesPagedSubscribable(accessToken: String, page: Int
    ): Observable<PlacesWrapper> {
        return Observable.just(PlacesWrapper(Arrays.asList(PlaceItem(), PlaceItem())))
        /*
        return api.getPlacesPagedObservable(
                accessToken,
                page
        ).subscribeOn(Schedulers.io())
                .delay(5000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                */
    }

    /*
    val placesCall: Call<PlacesWrapper>
        get() = api.getPlacesCall()
    */
    companion object {
        val BASE_URL = "http://jsonplaceholder.typicode.com"
        val USERS_URL = BASE_URL + "/users"
        //public static final String PLACES_URL = "https://github.com/hilfritz/Android-HBMvp/blob/development/temp/places/places.json";

        //THE URL HERE IS AFTER PASTING THE URL FROM GITHUB TO RAWGIT.COM
        const val PLACES_URL = "https://rawgit.com/hilfritz/Android-HBMvp/development/temp/places/places.json"


        /**

         * @param subscription Subscription - this is the rxjava subscription object
         */
        /*
        fun unsubscribe(subscription: Subscription?) {
            if (subscription != null && subscription!!.isUnsubscribed() === false) {
                subscription!!.unsubscribe()
            }
        }
        */
    }
}