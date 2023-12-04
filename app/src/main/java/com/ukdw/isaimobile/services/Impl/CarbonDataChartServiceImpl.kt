package com.ukdw.isaimobile.services.Impl

import android.graphics.Color
import android.graphics.Paint
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.ukdw.isaimobile.services.CarbonDataChartService


open class CarbonDataChartServiceImpl: CarbonDataChartService {
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

    override fun setDataCarbon(entryDataSize: Int): MutableList<CandleEntry> {
        val entries: MutableList<CandleEntry> = ArrayList()
        for (i in 0 until entryDataSize) {
            val mul: Float = (entryDataSize.toFloat() * 0.1F) + 0.1F
            val `val` = (Math.random() * 0.1).toFloat() + mul
            val high = (Math.random() * 1.5).toFloat() + 0.8f
            val low = (Math.random() * 1.5).toFloat() + 0.8f
            val open = (Math.random() * 0.6).toFloat() + 0.1f
            val close = (Math.random() * 0.6).toFloat() + 0.1f
            val odd = i % 2 != 0
            entries.add(
                CandleEntry(
                    (i + 1).toFloat(), if(`val` + high > 3) 3.0F else `val` + high,
                    if(`val` - low < 0.0) 0.0F else `val` - low,
                    if (!odd) `val` + open else `val` - open,
                    if (odd) `val` - close else `val` + close
                )
            )
        }
        return entries
    }

    override fun loadDataCarbon(entries: MutableList<CandleEntry>): CandleData {

//        val entries: MutableList<CandleEntry> = ArrayList()

//        entries.add(CandleEntry(0f, 80f, 90f, 70f, 85f)) // Up (green)
//        entries.add(CandleEntry(1f, 85f, 95f, 75f, 88f)) // Up (green)
//        entries.add(CandleEntry(2f, 88f, 75f, 82f, 85f)) // Down (red)
//        entries.add(CandleEntry(3f, 85f, 70f, 78f, 72f)) // Down (red)
//        entries.add(CandleEntry(4f, 72f, 68f, 70f, 70f)) // Down (red)
//        entries.add(CandleEntry(5f, 70f, 85f, 68f, 82f)) // Up (green)
//        entries.add(CandleEntry(6f, 82f, 78f, 80f, 75f)) // Down (red)
//        entries.add(CandleEntry(7f, 75f, 70f, 73f, 72f)) // Down (red)
//        entries.add(CandleEntry(8f, 72f, 82f, 70f, 80f)) // Up (green)
//        entries.add(CandleEntry(9f, 80f, 88f, 75f, 85f)) // Up (green)
//        entries.add(CandleEntry(10f, 85f, 92f, 82f, 90f)) // Up (green)
//        entries.add(CandleEntry(11f, 90f, 98f, 88f, 95f)) // Up (green)
//        entries.add(CandleEntry(12f, 95f, 88f, 90f, 85f)) // Down (red)
//        entries.add(CandleEntry(13f, 85f, 78f, 82f, 72f)) // Down (red)
//        entries.add(CandleEntry(14f, 72f, 70f, 70f, 68f)) // Down (red)

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
}