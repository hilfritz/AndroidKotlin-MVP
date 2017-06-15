package com.hilfritz.samplekotlin.ui.placelist

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

/**
 * A placeholder fragment containing a simple view.
 */
class PlacesFragment : BaseFragment(), PlacesView{

    lateinit var list:RecyclerView
    lateinit var loading:View
    lateinit var fullScreenMessage: TextView
    lateinit var presenter:PlacesPresenterImpl



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter = PlacesPresenterImpl(this, activity, savedInstanceState)
        return inflater!!.inflate(R.layout.fragment_places, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViews()
        init()
        presenter.populate()
    }

    override fun showFullScreenMessage(message: String) {
        loading.visibility = View.GONE
        fullScreenMessage.visibility = View.VISIBLE
        list.visibility = View.GONE
        fullScreenMessage.text=message
    }

    override fun showList() {
        loading.visibility = View.GONE
        fullScreenMessage.visibility = View.GONE
        list.visibility = View.VISIBLE
    }

    override fun showDialog(tag:String, message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun findViews() {
        fullScreenMessage = view!!.findViewById(R.id.fullScreenMessage) as TextView;
        loading = view!!.findViewById(R.id.loading);
        list = view!!.findViewById(R.id.recyclerView) as RecyclerView;


    }

    override fun showLoading() {
        loading.visibility= View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility= View.GONE
    }

    override fun init() {
        //INITIALIZE THE LIST
        list?.let{  //same as if (list!=null)
            list.setHasFixedSize(true)
            var lm = LinearLayoutManager(activity)
            lm.orientation = LinearLayoutManager.VERTICAL
            //list.layoutManager =
            list.layoutManager = lm

        }
    }

    override fun hideDialog(tag: String) {
    }

}
