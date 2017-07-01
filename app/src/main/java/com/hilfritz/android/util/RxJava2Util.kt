package com.hilfritz.android.util

import io.reactivex.disposables.Disposable

/**
 * Created by Hilfritz Camallere on 16/6/17.
 *
 */
object RxJava2Util {
    private val TAG = "RxUtil"

    /**
     *
     * @return Boolean true if the the disposable parameter is still running, false otherwise
     */
    fun isProcessing(disposable: Disposable?):Boolean{
        disposable?.let {
            if(disposable.isDisposed==false)
                return true
        }
        return false
    }

    /**
     * @param subscription
     * *
     * @return boolean TRUE if subscrption is still on going, (like network request is still on going)
     */
    /*
    fun isSubscribed(subscription: Subscription?): Boolean {
        Log.d(TAG, "isSubscribed: ")
        if (subscription != null && subscription!!.isUnsubscribed() === false) {
            Log.d(TAG, "isSubscribed: true")
            return true
        }
        return false
    }
    */

    /**
     * Used to unsubscribe a subscription from RX
     * @param subscription
     */
    /*
    fun unsubscribe(subscription: Subscription) {
        if (RxUtil.isSubscribed(subscription)) {
            Log.d(TAG, "unsubscribe: ")
            subscription.unsubscribe()
        }
    }
    */
    /**
     * Creates an observable object from a view that triggers when the view is clicked
     * @param view
     * *
     * @return
     */
    /*
    fun getViewClickObservable(view: View): Observable<View> {
        val clickEventObservable = Observable.create(object : Observable.OnSubscribe<View>() {
            fun call(subscriber: Subscriber<in View>) {
                view.setOnClickListener(View.OnClickListener { v ->
                    if (subscriber.isUnsubscribed()) return@OnClickListener
                    subscriber.onNext(v)
                })
            }
        })
        return clickEventObservable
    }
    */

}