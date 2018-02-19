package com.dega.park_kotlin

import com.dega.park_kotlin.model.Vehicle
import com.dega.park_kotlin.model.VehiclesResponse

/**
 * Created by davedega on 19/02/18.
 */
interface ParkContract {

    interface Presenter {
        //ANDR-100 load vehicles from API
        fun loadVehicles()
        //ANDR-300 select vehicle and show detail in new view
        fun showDetailInNewView(vehicle: Vehicle)
    }

    interface View {
        //ANDR-200 display vehicles in list view
        fun showVehiclesInList(vehiclesResponse: VehiclesResponse)

        fun showErrorMessage(message: Int)

        fun showErrorMessage(message: String)

        fun showLastUpdate()
    }
}
