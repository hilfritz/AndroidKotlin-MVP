package com.hilfritz.samplekotlin.ui.placelist.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.hilfritz.samplekotlin.BaseFragment
import com.hilfritz.samplekotlin.R
import com.hilfritz.samplekotlin.ui.placelist.PlacesPresenterImpl
import com.hilfritz.samplekotlin.ui.placelist.interfaces.PlacesView
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * A placeholder fragment containing a simple view.
 */
class PlacesFragment : BaseFragment(), PlacesView {

    lateinit var list: RecyclerView
    lateinit var loading: View
    lateinit var fullScreenMessage: TextView
    lateinit var presenter: PlacesPresenterImpl

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter = PlacesPresenterImpl(this, activity, savedInstanceState, AndroidSchedulers.mainThread())
        return inflater!!.inflate(R.layout.fragment_places, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //VIEW INITIALIZATIONS
        __findViews()
        __init()

        //PRESENTER INITIALIZATIONS
        presenter.__init(activity,savedInstanceState?: Bundle(),this)
        presenter.__populate()
    }

    override fun __showFullScreenMessage(message: String) {
        loading.visibility = View.GONE
        fullScreenMessage.visibility = View.VISIBLE
        list.visibility = View.GONE
        fullScreenMessage.text=message
    }

    override fun _showList() {
        loading.visibility = View.GONE
        fullScreenMessage.visibility = View.GONE
        list.visibility = View.VISIBLE
    }

    override fun __showDialog(tag:String, message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun __findViews() {
        fullScreenMessage = view!!.findViewById(R.id.fullScreenMessage) as TextView;
        loading = view!!.findViewById(R.id.loading);
        list = view!!.findViewById(R.id.recyclerView) as RecyclerView;


    }

    override fun __showLoading() {
        loading.visibility= View.VISIBLE
    }

    override fun __hideLoading() {
        loading.visibility= View.GONE
    }

    override fun __init() {
        //INITIALIZE THE LIST
        list?.let{  //same as if (list!=null)
            list.setHasFixedSize(true)
            var lm = LinearLayoutManager(activity)
            lm.orientation = LinearLayoutManager.VERTICAL
            //list.layoutManager =
            list.layoutManager = lm

        }
    }

    override fun __hideDialog(tag: String) {
    }

}
