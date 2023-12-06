package com.ukdw.isaimobile.ui.graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.formatter.ValueFormatter
import com.ukdw.isaimobile.R
import com.ukdw.isaimobile.services.CarbonDataChartService
import com.ukdw.isaimobile.services.Impl.CarbonDataChartServiceImpl


class CarbonEmissionFragment : Fragment() {
    private lateinit var barChart: BarChart
    private var carbonDataChartService: CarbonDataChartService =  object :
        CarbonDataChartServiceImpl(){}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carbon_emission, container, false)

        /* Ini buat candle stick chart */
        /*val candleStickChart: CandleStickChart = view.findViewById(R.id.candleStick)
        candleStickChart.description.text = ""
        val entries = carbonDataChartService.setDataCarbon()
        val data = carbonDataChartService.loadDataCarbon(entries)

        candleStickChart.data = data

        val xAxis: XAxis = candleStickChart.getXAxis()
        xAxis.textSize = 12F
        xAxis.valueFormatter = object : ValueFormatter(){
            override fun getFormattedValue(value: Float): String {
                return carbonDataChartService.getXAxisLabel()[value.toInt()]
            }
        }
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.labelCount = 12

        val yAxisRight: YAxis = candleStickChart.getAxisRight()
        yAxisRight.isEnabled = false

        candleStickChart.legend.isEnabled = false
        candleStickChart.invalidate()*/
        /* Sampe sini */

        /* Ini buat bar chart */

        barChart = view.findViewById(R.id.idBarChartEmission)
        barChart.clear()
        carbonDataChartService.refreshData()

        carbonDataChartService.setDataBarChart(12, "EM")
        barChart.data = carbonDataChartService.loadDataBarChart(barChart, 0.38f, 0.01f, 0.2F, "EM")
        barChart.description.isEnabled = false
        barChart.legend.isEnabled = false

        val xAxis: XAxis = barChart.getXAxis()
        xAxis.textSize = 12F
        xAxis.valueFormatter = object : ValueFormatter(){
            override fun getFormattedValue(value: Float): String {
                return carbonDataChartService.getXAxisLabel()[value.toInt()]
            }
        }
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(true)
        xAxis.labelCount = 12
        xAxis.setCenterAxisLabels(false)

        val yAxisRight: YAxis = barChart.axisRight
        yAxisRight.isEnabled = false
        barChart.invalidate()
        /* Sampe sini */

        return view
    }
}