package com.ukdw.isaimobile.services

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleEntry

interface CarbonDataChartService {
    fun getXAxisLabel(): ArrayList<String>
    fun getXAxisBarLabel(): ArrayList<String>
    fun setDataCarbon(entryDataSize: Int = 10): MutableList<CandleEntry>
    fun loadDataCarbon(entries: MutableList<CandleEntry>): CandleData
    fun setLegendsCandle(candleStickChart: CandleStickChart)

    fun setDataBarChart(size: Int, tab: String)

    fun loadDataBarChart(barChart: BarChart, groupSpace: Float, barSpace: Float, barWidth: Float, tab: String): BarData

    fun refreshData()
}