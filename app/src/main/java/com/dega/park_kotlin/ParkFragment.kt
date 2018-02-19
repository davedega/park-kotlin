package com.dega.park_kotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.dega.park_kotlin.model.Vehicle
import com.dega.park_kotlin.model.VehiclesResponse
import kotlinx.android.synthetic.main.park_fragment.*
import java.text.DateFormat
import java.util.*

/**
 * Created by davedega on 17/02/18.
 */
class ParkFragment : Fragment(), ParkContract.View {


    lateinit var presenter: ParkContract.Presenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.park_fragment, container, false)


    override fun showVehiclesInList(vehiclesResponse: VehiclesResponse) {
        vehiclesRecyclerView.visibility = View.VISIBLE
        logoImageView.visibility = View.GONE

        val mLayoutManager = LinearLayoutManager(activity.applicationContext)
        vehiclesRecyclerView.layoutManager = mLayoutManager

        val adapterGrass = VehiclesAdapter(ArrayList(vehiclesResponse.vehicles))

        vehiclesRecyclerView.adapter = adapterGrass

    }

    override fun showErrorMessage(message: Int) {
        logoImageView.visibility = View.VISIBLE
        vehiclesRecyclerView.visibility = View.GONE
        Snackbar.make(logoImageView!!,
                message, Snackbar.LENGTH_SHORT)
                .setAction(R.string.try_again) { presenter.loadVehicles() }
                .show()
    }

    override fun showErrorMessage(message: String) {
        logoImageView.visibility = View.VISIBLE
        vehiclesRecyclerView!!.visibility = View.GONE
        val mySnackbar = Snackbar.make(logoImageView!!,
                message, Snackbar.LENGTH_SHORT)
        mySnackbar.setAction(R.string.try_again) { presenter!!.loadVehicles() }

        mySnackbar.show()
    }

    override fun showLastUpdate() {
        val date = Date()
        val stringDate = DateFormat.getTimeInstance(DateFormat.SHORT).format(date)
        Snackbar.make(logoImageView!!,
                getString(R.string.last_update, stringDate), Snackbar.LENGTH_LONG).show()
    }

    // The Adapter lives within the view since is the only class who access it
    internal inner class VehiclesAdapter(var vehicles: List<Vehicle>) :
            RecyclerView.Adapter<VehiclesAdapter.VehicleViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder =
                VehicleViewHolder(LayoutInflater.from(activity.applicationContext).inflate(R.layout.vehicle_list_item, parent, false))


        override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
            vehicles[position].let {
                holder.bind(it, presenter)
            }
        }

        override fun getItemCount(): Int = vehicles.size

        internal inner class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var vehicleName: TextView
            var vehicleItem: LinearLayout

            init {
                this.vehicleName = itemView.findViewById(R.id.vehicleName)
                this.vehicleItem = itemView.findViewById(R.id.vehicleItem)
            }

            fun bind(vehicle: Vehicle, presenter: ParkContract.Presenter) {
                vehicleName.text = vehicle.vrn
                vehicleItem.setOnClickListener { presenter.showDetailInNewView(vehicle) }
            }
        }
    }
}
