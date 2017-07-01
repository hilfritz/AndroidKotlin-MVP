package com.hilfritz.android.barfinder.ui.details

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hilfritz.android.R

/**
 * A placeholder fragment containing a simple view.
 */
class DetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_details, container, false)
    }
}
