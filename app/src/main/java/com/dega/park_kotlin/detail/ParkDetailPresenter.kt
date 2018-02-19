package com.dega.park_kotlin.detail

import com.dega.park_kotlin.R
import com.dega.park_kotlin.model.Vehicle

/**
 * Created by ddelgado on 19/02/2018.
 */
class ParkDetailPresenter(var view: ParkDetailContract.View) : ParkDetailContract.Presenter {
    override fun loadDetailVehicle(vehicle: Vehicle?) = if (vehicle != null) view.showVehicleDetail(vehicle) else view.showErrorMessage(R.string.no_details_for_this_car)
}