package com.dega.park_kotlin.detail

import com.dega.park_kotlin.model.Vehicle

/**
 * Created by davedega on 19/02/18.
 */
interface ParkDetailContract {

    interface Presenter {
        fun loadDetailVehicle(vehicle: Vehicle?)
    }

    interface View {

        fun showErrorMessage(message: Int)

        fun showVehicleDetail(vehicle: Vehicle?)

    }
}
