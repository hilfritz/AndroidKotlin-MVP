package com.hilfritz.samplekotlin.ui.placelist.helper

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.facebook.drawee.view.SimpleDraweeView
import com.hilfritz.samplekotlin.R
import com.hilfritz.samplekotlin.api.pojo.PlaceItem
import com.hilfritz.samplekotlin.databinding.ListItemPlaceBinding
import com.hilfritz.samplekotlin.ui.placelist.interfaces.PlacesPresenterInterface
import java.util.*

/**
 * Created by Hilfritz Camallere on 25/5/17.
 *  * @see https://mutualmobile.com/posts/using-data-binding-api-in-recyclerview
 */

class PlaceListAdapter(list: ArrayList<PlaceItem>, internal var presenter: PlacesPresenterInterface) : RecyclerView.Adapter<PlaceListAdapter.ViewHolder>() {
    internal var list = ArrayList<PlaceItem>()

    init {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = DataBindingUtil.inflate<ListItemPlaceBinding>(
                LayoutInflater.from(parent.context),
                R.layout.list_item_place,
                parent,
                false
        )
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindConnection(list[position])
        loadImage(holder.itemView.findViewById(R.id.image) as SimpleDraweeView, list[position].pictureUrl!!)
        //(holder.itemView.findViewById(R.id.name) as TextView).text=list[position].pictureUrl
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private val binding: ListItemPlaceBinding) : RecyclerView.ViewHolder(binding.getRoot()) {
        fun bindConnection(userWrapper: PlaceItem) {
            binding.setVm(userWrapper)
            binding.setPresenter(presenter)
          }
    }

    companion object {
        private val TAG = "PlaceListAdapter"
        /*
        @BindingAdapter("bind:loadImage")
        fun loadImage(simpleDraweeView: SimpleDraweeView, url: String) {
            //Log.d(TAG, "loadImage: url:"+url);
            simpleDraweeView.setImageURI(Uri.parse(url))
        }
        */
    }

    @BindingAdapter("bind:loadImage")
    fun loadImage(simpleDraweeView: SimpleDraweeView, url: String) {
        //Log.d(TAG, "loadImage: url:"+url);
        simpleDraweeView.setImageURI(Uri.parse(url))
    }

}

