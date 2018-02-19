package com.dega.park_kotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by davedega on 19/02/18.
 */
data class VehiclesResponse(
        @SerializedName("count")
        @Expose
        var count: Int?,
        @SerializedName("vehicles")
        @Expose
        var vehicles: List<Vehicle>?,
        @SerializedName("currentPage")
        @Expose
        var currentPage: Int?,
        @SerializedName("nextPage")
        @Expose
        var nextPage: Int?,
        @SerializedName("totalPages")
        @Expose
        var totalPages: Int? = null
)
