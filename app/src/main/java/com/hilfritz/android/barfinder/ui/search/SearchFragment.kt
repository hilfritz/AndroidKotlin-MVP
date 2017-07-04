package com.hilfritz.android.barfinder.ui.search

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hilfritz.android.BaseActivity
import com.hilfritz.android.BaseFragment
import com.hilfritz.android.R
import com.hilfritz.android.api.pojo.PlaceItem
import com.hilfritz.android.ui.placelist.helper.PlaceListAdapter
import com.hilfritz.android.ui.placelist.interfaces.PlacesPresenterInterface
import java.util.*

/**
 * A placeholder fragment containing a simple view.
 */
class SearchFragment : BaseFragment(), SearchViewInterface {
    override fun __isFinishing(): Boolean {
        return __getActivity().isFinishing
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_search, container, false)
    }

    override fun __findViews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun __showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun __hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun __showFullScreenMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun __showDialog(tag: String, message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun __hideDialog(tag: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun __initViews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun __getActivity(): BaseActivity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun _showList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun _createAdapter(list: ArrayList<PlaceItem>, presenter: PlacesPresenterInterface): PlaceListAdapter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun _reInitializeRecyeclerView(list: ArrayList<PlaceItem>, presenter: PlacesPresenterInterface) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun _notifyDataSetChangedRecyeclerView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun _notifyDataSetChangedRecyeclerView(index: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun _getRecyclerView(): RecyclerView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
