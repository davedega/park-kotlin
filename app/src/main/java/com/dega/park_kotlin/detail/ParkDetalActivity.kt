package com.dega.park_kotlin.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dega.park_kotlin.R
import com.dega.park_kotlin.model.Constants
import com.dega.park_kotlin.model.Vehicle
import kotlinx.android.synthetic.main.activity_park_detail.*

/**
 * Created by davedega on 19/02/18.
 */
class ParkDetalActivity : AppCompatActivity() {

    lateinit var presenter: ParkDetailPresenter
    lateinit var view: ParkDetailContract.View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_park_detail)

        view = parkDetailFragment as ParkDetailContract.View

        presenter = ParkDetailPresenter(view)

        (view as ParkDetailFragment).presenter = presenter

        presenter.loadDetailVehicle(vehicleFromBundle(intent.extras))
    }


    private fun vehicleFromBundle(bundle: Bundle?): Vehicle {
        lateinit var vehicle: Vehicle
        bundle?.let {
            vehicle = Vehicle(it.getInt(Constants.Data.VEHICLE_ID),
                    it.getString(Constants.Data.VRN),
                    it.getString(Constants.Data.COUNTRY),
                    it.getString(Constants.Data.COLOR),
                    it.getString(Constants.Data.TYPE),
                    it.getBoolean(Constants.Data.DEFAULT))
        }
        return vehicle
    }
}
