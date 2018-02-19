package com.dega.park_kotlin.detail

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dega.park_kotlin.R
import com.dega.park_kotlin.model.Vehicle
import kotlinx.android.synthetic.main.park_detail_fragment.*

/**
 * Created by davedega on 19/02/18.
 */
class ParkDetailFragment : Fragment(), ParkDetailContract.View {

    lateinit var presenter: ParkDetailPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater!!.inflate(R.layout.park_detail_fragment, container, false)

    override fun showErrorMessage(message: Int) = Snackbar.make(rootView, getString(R.string.no_details_for_this_car), Snackbar.LENGTH_LONG).show()

    override fun showVehicleDetail(vehicle: Vehicle?) {
        vehicle?.let {
            vehicleIdTv.text = it.vehicleId.toString()
            vrnTv.text = it.vrn
            countryTv.text = it.country
            colorTv.text = it.color
            typeTv.text = it.type
            defaultSwitch.isSelected = it.default as Boolean
        }
    }
}
