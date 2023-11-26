package com.ukdw.isaimobile.ui.graph

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.ukdw.isaimobile.R


class GraphFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_graph, container, false)
        val candleStickChart: CandleStickChart = view.findViewById(R.id.candleStick)

        // This Description is visible at Bottom Right side

        // This Description is visible at Bottom Right side
        candleStickChart.description.text = "GFG"

        // Creating a list to store CandleEntry objects

        // Creating a list to store CandleEntry objects
        val entries: MutableList<CandleEntry> = ArrayList()

        // Added candlestick dummy data entries here
        // Format will be like entries.add(new CandleEntry(index, high, low, open, close));
        // here f is denoting floating-point number

        // Added candlestick dummy data entries here
        // Format will be like entries.add(new CandleEntry(index, high, low, open, close));
        // here f is denoting floating-point number
        entries.add(CandleEntry(0f, 80f, 90f, 70f, 85f)) // Up (green)
        entries.add(CandleEntry(1f, 85f, 95f, 75f, 88f)) // Up (green)
        entries.add(CandleEntry(2f, 88f, 75f, 82f, 85f)) // Down (red)
        entries.add(CandleEntry(3f, 85f, 70f, 78f, 72f)) // Down (red)
        entries.add(CandleEntry(4f, 72f, 68f, 70f, 70f)) // Down (red)
        entries.add(CandleEntry(5f, 70f, 85f, 68f, 82f)) // Up (green)
        entries.add(CandleEntry(6f, 82f, 78f, 80f, 75f)) // Down (red)
        entries.add(CandleEntry(7f, 75f, 70f, 73f, 72f)) // Down (red)
        entries.add(CandleEntry(8f, 72f, 82f, 70f, 80f)) // Up (green)
        entries.add(CandleEntry(9f, 80f, 88f, 75f, 85f)) // Up (green)
        entries.add(CandleEntry(10f, 85f, 92f, 82f, 90f)) // Up (green)
        entries.add(CandleEntry(11f, 90f, 98f, 88f, 95f)) // Up (green)
        entries.add(CandleEntry(12f, 95f, 88f, 90f, 85f)) // Down (red)
        entries.add(CandleEntry(13f, 85f, 78f, 82f, 72f)) // Down (red)
        entries.add(CandleEntry(14f, 72f, 70f, 70f, 68f)) // Down (red)

        // Created a CandleDataSet from the entries

        // Created a CandleDataSet from the entries
        val dataSet = CandleDataSet(entries, "Data")

        dataSet.setDrawIcons(false)
        dataSet.increasingColor = Color.GREEN // Color for up (green) candlesticks

        dataSet.increasingPaintStyle =
            Paint.Style.FILL // Set the paint style to Fill for green candlesticks

        dataSet.decreasingColor = Color.RED // Color for down (red) candlesticks

        dataSet.shadowColorSameAsCandle =
            true // Using the same color for shadows as the candlesticks

        dataSet.setDrawValues(false) // Hiding the values on the chart if not needed


        // Created a CandleData object from the CandleDataSet

        // Created a CandleData object from the CandleDataSet
        val data = CandleData(dataSet)

        // Seinft the CandleData to the CandleStickChart

        // Seinft the CandleData to the CandleStickChart
        candleStickChart.data = data
        candleStickChart.invalidate()
        return view
    }
}