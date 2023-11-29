package com.ukdw.isaimobile.services

import com.ukdw.isaimobile.dto.LocationCarbon

interface LoadLocationCarbonService {
    fun setData()
    fun loadData(): ArrayList<LocationCarbon>
}