package com.dega.park_kotlin

import com.dega.park_kotlin.model.VehiclesResponse

/**
 * Created by davedega on 19/02/18.
 */
interface ParkContract {

    interface Presenter {
        fun loadVehicles()
    }

    interface View {

        fun showVehiclesInList(vehiclesResponse: VehiclesResponse)

        fun showErrorMessage(message: Int)

        fun showErrorMessage(message: String)

        fun showLastUpdate()
    }
}
