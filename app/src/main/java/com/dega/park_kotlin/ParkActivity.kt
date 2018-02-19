package com.dega.park_kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_park.*

/**
 * Created by davedega on 19/02/18.
 */
class ParkActivity : AppCompatActivity() {

    private lateinit var presenter: ParkPresenter
    private lateinit var view: ParkContract.View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_park)

        view = parkFragment as ParkContract.View
        presenter = ParkPresenter(view, applicationContext)

        (view as ParkFragment).presenter = presenter
        presenter.loadVehicles()
    }
}
