package com.hilfritz.android.ui.placelist.view

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.hilfritz.android.*
import com.hilfritz.android.api.RestApiInterface
import com.hilfritz.android.api.pojo.PlaceItem
import com.hilfritz.android.ui.placelist.PlacesPresenterImpl
import com.hilfritz.android.ui.placelist.helper.PlaceListAdapter
import com.hilfritz.android.ui.placelist.interfaces.PlacesPresenterInterface
import com.hilfritz.android.ui.placelist.interfaces.PlacesView
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class PlacesFragment : BaseFragment(), PlacesView {

    override fun __getActivity(): BaseActivity {
        return activity as BaseActivity
    }

    lateinit var list: RecyclerView
    lateinit var refreshLayout: SwipeRefreshLayout
    lateinit var loading: View
    lateinit var fullScreenMessage: TextView

    @Inject
    lateinit var restApiManager: RestApiInterface

    @Inject
    lateinit var presenter: PlacesPresenterImpl

    lateinit var adapter: PlaceListAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity.application as MyApplication).appComponent.inject(this)
        return inflater!!.inflate(R.layout.fragment_places, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //VIEW INITIALIZATIONS
        __findViews()
        __initViews()

        //PRESENTER INITIALIZATIONS
        presenter.apiManager = restApiManager
        presenter.__init(activity,savedInstanceState?: Bundle(),this, AndroidSchedulers.mainThread())
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
        refreshLayout = view!!.findViewById(R.id.swipeRefreshLayout) as SwipeRefreshLayout;

    }

    override fun __showLoading() {
        loading.visibility= View.VISIBLE
        refreshLayout.isRefreshing = true
    }

    override fun __hideLoading() {
        loading.visibility= View.GONE
        refreshLayout.isRefreshing = false
    }

    override fun __initViews() {
        //INITIALIZE THE LIST
        list?.let{  //same as if (list!=null)
            list.setHasFixedSize(true)

            //HANDLE ORIENTATION CHANGE OF RECYCLERVIEW,
            if (activity.resources.configuration.orientation === Configuration.ORIENTATION_PORTRAIT) {
                //1 COLUMN IF PORTRAIT
                val llm = LinearLayoutManager(activity)
                llm.orientation = LinearLayoutManager.VERTICAL
                list.setLayoutManager(llm)
            } else {
                //2 COLUMNS IF LANDSCAPE
                list.setLayoutManager(GridLayoutManager(activity, 2))
            }
        }

        refreshLayout?.let {
            refreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
                presenter._refresh()
            })
        }
    }

    override fun __hideDialog(tag: String) {
    }

    override fun _getRecyclerView(): RecyclerView {
        return list
    }

    override fun _reInitializeRecyeclerView(list: ArrayList<PlaceItem>, presenter: PlacesPresenterInterface) {
        this.adapter = _createAdapter(list, presenter)
        this.list.adapter = this.adapter
        this.adapter.notifyDataSetChanged()
    }

    override fun _createAdapter(list: ArrayList<PlaceItem>, presenter: PlacesPresenterInterface): PlaceListAdapter {
        return PlaceListAdapter(list,presenter)
    }
    override fun _notifyDataSetChangedRecyeclerView() {
        this.adapter.notifyDataSetChanged()
    }
    override fun _notifyDataSetChangedRecyeclerView(index: Int) {
        this.adapter.notifyItemChanged(index)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.__destroy()
    }
}
