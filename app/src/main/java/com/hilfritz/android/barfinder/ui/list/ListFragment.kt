package com.hilfritz.android.barfinder.ui.list

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hilfritz.android.R

/**
 * A placeholder fragment containing a simple view.
 */
class ListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_list, container, false)
    }
}
