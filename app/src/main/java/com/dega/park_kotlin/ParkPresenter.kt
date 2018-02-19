package com.dega.park_kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.dega.park_kotlin.api.ParkApi
import com.dega.park_kotlin.detail.ParkDetalActivity
import com.dega.park_kotlin.model.Constants
import com.dega.park_kotlin.model.Vehicle
import com.dega.park_kotlin.model.VehiclesResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException

/**
 * Created by davedega on 19/02/18.
 */
class ParkPresenter internal constructor(val view: ParkContract.View, val context: Context) : ParkContract.Presenter {

    private val API_BASE_URL = "http://private-6d86b9-vehicles5.apiary-mock.com/"
    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_BASE_URL)
                .build()
    }

    override fun loadVehicles() {
        val parkApi = retrofit.create<ParkApi>(ParkApi::class.java)

        val vehicleClient = parkApi.loadVehicles()

        vehicleClient.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableObserver<VehiclesResponse>() {
                    override fun onNext(vehiclesResponse: VehiclesResponse) {
                        view.showVehiclesInList(vehiclesResponse)
                    }

                    override fun onError(e: Throwable) {
                        when (e) {
                            is UnknownHostException -> {
                                view.showErrorMessage(R.string.no_internet_connection)
                            }
                            is HttpException -> {
                                view.showErrorMessage(R.string.not_found)
                            }
                            else -> {
                                view.showErrorMessage(e.message.toString())
                            }
                        }
                    }

                    override fun onComplete() {
                        view.showLastUpdate()
                    }
                })
    }

    override fun showDetailInNewView(vehicle: Vehicle) {
        val extras = Bundle()
        extras.putInt(Constants.Data.VEHICLE_ID, vehicle.vehicleId!!)
        extras.putString(Constants.Data.VRN, vehicle.vrn!!)
        extras.putString(Constants.Data.COUNTRY, vehicle.country!!)
        extras.putString(Constants.Data.COLOR, vehicle.color!!)
        extras.putString(Constants.Data.TYPE, vehicle.type!!)
        extras.putBoolean(Constants.Data.DEFAULT, vehicle.default!!)

        val detail = Intent(context, ParkDetalActivity::class.java)
        detail.putExtras(extras)
        context.startActivity(detail)
    }
}
