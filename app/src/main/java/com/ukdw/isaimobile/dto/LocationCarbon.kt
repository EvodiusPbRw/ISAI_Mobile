package com.ukdw.isaimobile.dto

import java.util.Date

data class LocationCarbon(
    val area: String,
    val longitude: Double,
    val latitude: Double,
    val sampleCode: String,
    val landName: String,
    val commodity: String,
    val samplingDate: Date,
    val soilCarbon: Double,
    val plantCarbon: Double
)
