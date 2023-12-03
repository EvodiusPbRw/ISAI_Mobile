package com.ukdw.isaimobile.services

import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleEntry

interface CarbonDataChartService {
    fun getXAxisLabel(): ArrayList<String>
    fun setDataCarbon(entryDataSize: Int = 11): MutableList<CandleEntry>
    fun loadDataCarbon(entries: MutableList<CandleEntry>): CandleData
}