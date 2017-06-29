package com.hilfritz.samplekotlin.ui.placelist.helper

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.hilfritz.samplekotlin.R
import com.hilfritz.samplekotlin.api.pojo.PlaceItem
import com.hilfritz.samplekotlin.ui.placelist.interfaces.PlacesPresenterInterface
import java.util.*





/**
 * Created by Hilfritz Camallere on 25/5/17.
 *  * @see https://mutualmobile.com/posts/using-data-binding-api-in-recyclerview
 */

class PlaceListAdapter(list: ArrayList<PlaceItem>, internal var presenter: PlacesPresenterInterface) : RecyclerView.Adapter<PlaceListAdapter.ViewHolderWithoutDataBinding>() {

    internal var list = ArrayList<PlaceItem>()

    init {
        this.list = list
    }

    /*
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = DataBindingUtil.inflate<ListItemPlaceBinding>(
                LayoutInflater.from(parent.context),
                R.layout.list_item_place,
                parent,
                false
        )
        return ViewHolder(binding)
    }
    */

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderWithoutDataBinding {
        val itemView = LayoutInflater.from(parent!!.getContext())
                .inflate(R.layout.list_item_place_without_databinding, parent, false)
        return ViewHolderWithoutDataBinding(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolderWithoutDataBinding, position: Int) {
        holder.bind()
        loadImage(holder.image, list[position].pictureUrl!!)
        holder.name.text = list[position].name
        holder.selectedIndicator.visibility = list[position].isSelected
        holder.selectedIndicator.setOnClickListener ({
            presenter._onListItemClick(list[position])
        })
        holder.relativeLayout.setOnClickListener({
            presenter._onListItemClick(list[position])
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /*
    inner class ViewHolderWithDataBinding(private val binding: ListItemPlaceBinding) : RecyclerView.ViewHolder(binding.getRoot()) {
        fun bindConnection(userWrapper: PlaceItem) {
            binding.setVm(userWrapper)
            binding.setPresenter(presenter)
          }
    }
    */

    data class ViewHolderWithoutDataBinding(var view: View?) : RecyclerView.ViewHolder(view) {
        lateinit var name:TextView
        lateinit var image:SimpleDraweeView
        lateinit var relativeLayout:RelativeLayout
        lateinit var selectedIndicator:View
        fun bind(){
            name = view!!.findViewById(R.id.name) as TextView
            image = view!!.findViewById(R.id.image) as SimpleDraweeView
            relativeLayout = view!!.findViewById(R.id.relativeLayout) as RelativeLayout
            selectedIndicator = view!!.findViewById(R.id.selected_indicator)
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


    //@BindingAdapter("bind:loadImage")
    fun loadImage(simpleDraweeView: SimpleDraweeView, url: String) {
        //Log.d(TAG, "loadImage: url:"+url);
        simpleDraweeView.setImageURI(Uri.parse(url))
    }

}

