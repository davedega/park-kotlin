package com.dega.park_kotlin.api

import com.dega.park_kotlin.model.VehiclesResponse

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by davedega on 19/02/18.
 */
interface ParkApi {
    @GET("/vehicles")
    fun loadVehicles(): Observable<VehiclesResponse>
}
