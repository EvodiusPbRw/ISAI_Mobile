package com.ukdw.isaimobile.services.Impl

import android.util.Log
import com.ukdw.isaimobile.dto.LocationCarbon
import com.ukdw.isaimobile.services.LoadLocationCarbonService
import java.text.SimpleDateFormat

open class LoadLocationCarbonServiceImpl: LoadLocationCarbonService {
    override fun setData() {
        Log.d("Check", "Test")
    }

    override fun loadData(): ArrayList<LocationCarbon> {
        val data: ArrayList<LocationCarbon> = ArrayList()
        data.add(LocationCarbon(
            area = "Tulung",
            longitude = 110.481451,
            latitude = -7.741573,
            sampleCode = "T-1",
            landName = "Tulung Fase 1",
            commodity = "Padi",
            samplingDate = SimpleDateFormat("yyyy-MM-dd").parse("2023-09-30"),
            soilCarbon = 2.22,
            plantCarbon = 2.22
        ))
        data.add(LocationCarbon(
            area = "Tulung",
            longitude = 110.481570,
            latitude = -7.741952,
            sampleCode = "T-2",
            landName = "Tulung Fase 2",
            commodity = "Padi",
            samplingDate = SimpleDateFormat("yyyy-MM-dd").parse("2023-10-30"),
            soilCarbon = 2.22,
            plantCarbon = 2.22
        ))
        data.add(LocationCarbon(
            area = "Kowang",
            longitude = 110.479132,
            latitude = -7.766405,
            sampleCode = "K-1",
            landName = "Kowang Fase 1",
            commodity = "Padi",
            samplingDate = SimpleDateFormat("yyyy-MM-dd").parse("2023-08-30"),
            soilCarbon = 2.22,
            plantCarbon = 2.22
        ))
        data.add(LocationCarbon(
            area = "Kowang",
            longitude = 110.478102,
            latitude = -7.767123,
            sampleCode = "K-2",
            landName = "Kowang Fase 2",
            commodity = "Padi",
            samplingDate = SimpleDateFormat("yyyy-MM-dd").parse("2023-11-30"),
            soilCarbon = 2.22,
            plantCarbon = 2.22
        ))
        return data
    }

}