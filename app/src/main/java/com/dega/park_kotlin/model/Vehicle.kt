package com.dega.park_kotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by davedega on 19/02/18.
 */
data class Vehicle(
        @SerializedName("vehicleId")
        @Expose
        var vehicleId: Int?,
        @SerializedName("vrn")
        @Expose
        var vrn: String?,
        @SerializedName("country")
        @Expose
        var country: String?,
        @SerializedName("color")
        @Expose
        var color: String?,
        @SerializedName("type")
        @Expose
        var type: String?,
        @SerializedName("default")
        @Expose
        var default: Boolean? = null
)