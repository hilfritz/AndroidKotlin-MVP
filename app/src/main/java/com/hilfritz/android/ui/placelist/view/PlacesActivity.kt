package com.hilfritz.android.ui.placelist.view

import com.hilfritz.android.BaseActivity
import com.hilfritz.android.R
import com.hilfritz.android.util.log.Logger

class PlacesActivity : BaseActivity() {
    val TAG = "PlacesActivity";
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d(TAG, "onCreate")
        setContentView(R.layout.activity_places)
        val toolbar = findViewById(R.id.toolbar) as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)

    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.d(TAG, "onDestroy")
    }
}
