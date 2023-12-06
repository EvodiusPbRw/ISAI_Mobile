package com.ukdw.isaimobile.services.Impl

import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.ukdw.isaimobile.services.CarbonDataChartService
import kotlin.random.Random


open class CarbonDataChartServiceImpl: CarbonDataChartService {
    private var barPlantEntries: MutableList<BarEntry> = ArrayList()
    private var barLandEntries: MutableList<BarEntry> = ArrayList()
    private var barEnvEntries: MutableList<BarEntry> = ArrayList()

    override fun getXAxisLabel(): ArrayList<String> {
        val xvalue = ArrayList<String>()
        xvalue.add("Jan")
        xvalue.add("Feb")
        xvalue.add("Mar")
        xvalue.add("Apr")
        xvalue.add("Mei")
        xvalue.add("Jun")
        xvalue.add("Jul")
        xvalue.add("Aug")
        xvalue.add("Sep")
        xvalue.add("Oct")
        xvalue.add("Nov")
        xvalue.add("Des")
        return xvalue
    }

    override fun getXAxisBarLabel(): ArrayList<String> {
        val xvalue = ArrayList<String>()
        xvalue.add("HST0")
        xvalue.add("HST1")
        xvalue.add("HST2")
        return xvalue
    }


    override fun setDataCarbon(entryDataSize: Int): MutableList<CandleEntry> {
        val entries: MutableList<CandleEntry> = ArrayList()
//        for (i in 0..entryDataSize) {
//            val mul: Float = (entryDataSize.toFloat() * 0.1F) + 0.1F
//            val `val` = (Math.random() * 0.1).toFloat() + mul
//            val high = (Math.random() * 1.5).toFloat() + 0.8f
//            val low = (Math.random() * 1.5).toFloat() + 0.8f
//            val open = (Math.random() * 0.6).toFloat() + 0.1f
//            val close = (Math.random() * 0.6).toFloat() + 0.1f
//            val odd = i % 2 != 0
//            entries.add(
//                CandleEntry(
//                    (i + 1).toFloat(), if(`val` + high > 3) 3.0F else `val` + high,
//                    if(`val` - low < 0.0) 0.0F else `val` - low,
//                    if (!odd) `val` + open else `val` - open,
//                    if (odd) `val` - close else `val` + close
//                )
//            )
//        }
        entries.add(CandleEntry(0f, 80f, 90f, 70f, 85f))
        entries.add(CandleEntry(1f, 85f, 95f, 75f, 88f))
        entries.add(CandleEntry(2f, 88f, 75f, 82f, 85f))
        entries.add(CandleEntry(3f, 85f, 70f, 78f, 72f))
        entries.add(CandleEntry(4f, 72f, 68f, 70f, 70f))
        entries.add(CandleEntry(5f, 70f, 85f, 68f, 82f))
        entries.add(CandleEntry(6f, 82f, 78f, 80f, 75f))
        entries.add(CandleEntry(7f, 75f, 70f, 73f, 72f))
        entries.add(CandleEntry(8f, 72f, 82f, 70f, 80f))
        entries.add(CandleEntry(9f, 80f, 88f, 75f, 85f))
        entries.add(CandleEntry(10f, 85f, 92f, 82f, 90f))
        entries.add(CandleEntry(11f, 90f, 98f, 88f, 95f))
        return entries
    }

    override fun loadDataCarbon(entries: MutableList<CandleEntry>): CandleData {
        val dataSet = CandleDataSet(entries, "Defisit")

        dataSet.label = "Surplus"
        dataSet.setDrawIcons(false)
        dataSet.increasingColor = Color.parseColor("#0FA646")

        dataSet.increasingPaintStyle =
            Paint.Style.FILL

        dataSet.decreasingColor = Color.parseColor("#C71515")
        dataSet.shadowColorSameAsCandle = true

        dataSet.valueTextSize = 16F
        dataSet.setDrawValues(false)

        val data = CandleData(dataSet)
        dataSet.valueTextSize = 16F

        return data
    }
    override fun setLegendsCandle(candleStickChart: CandleStickChart) {
        val l: Legend = candleStickChart.legend
        l.entries
        l.yEntrySpace = 200f
        l.isWordWrapEnabled = true
        val l1 = LegendEntry("Male", Legend.LegendForm.CIRCLE, 10f, 2f, null, Color.YELLOW)
        val l2 = LegendEntry("Female", Legend.LegendForm.CIRCLE, 10f, 2f, null, Color.RED)
        l.setCustom(arrayOf(l1, l2))
        l.isWordWrapEnabled = true;
        l.isEnabled = false
    }

    override fun setDataBarChart(size: Int, tab: String) {
        Log.d("Check 9", size.toString())
        for (index in 0 until size) {
            barPlantEntries.add(BarEntry(index.toFloat(), (0..10).random().toFloat()/3F))
            barLandEntries.add(BarEntry(index.toFloat(), (0..10).random().toFloat()/3F))
            if(tab == "EM")
                barEnvEntries.add(BarEntry(index.toFloat(), (0..10).random().toFloat()/3F))
        }
    }

    override fun loadDataBarChart(barChart: BarChart, groupSpace: Float, barSpace: Float, barWidth: Float, tab: String): BarData {
        barChart.setFitBars(true)

        val set1 = BarDataSet(barPlantEntries, "Bar 1")
        set1.color = Color.rgb(66, 103, 153)
        set1.valueTextColor = Color.BLACK
        set1.valueTextSize = 10f
        set1.axisDependency = YAxis.AxisDependency.LEFT
        set1.setDrawValues(false)

        val set2 = BarDataSet(barLandEntries, "Bar 2")
        set2.setColors(Color.rgb(152, 180, 216))
        set2.valueTextColor = Color.BLACK
        set2.valueTextSize = 10f
        set2.axisDependency = YAxis.AxisDependency.LEFT
        set2.setDrawValues(false)

        val set3 = BarDataSet(barEnvEntries, "Bar 3")
        set3.setColors(Color.rgb(184, 198, 216))
        set3.valueTextColor = Color.BLACK
        set3.valueTextSize = 10f
        set3.axisDependency = YAxis.AxisDependency.LEFT
        set3.setDrawValues(false)

        var d = BarData(set1, set2)

        if(tab in listOf("EM"))
            d = BarData(set1, set2, set3)

        d.barWidth = barWidth

        d.groupBars(-0.5f, groupSpace, barSpace)
        return d
    }

    override fun refreshData() {
        barPlantEntries.clear()
        barLandEntries.clear()
    }
}