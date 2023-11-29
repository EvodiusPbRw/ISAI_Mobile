package com.ukdw.isaimobile.ui.home

//import com.mapbox.mapboxsdk.Mapbox
//import com.mapbox.mapboxsdk.maps.MapView
//import com.mapbox.mapboxsdk.maps.MapboxMap
//import com.mapbox.mapboxsdk.maps.Style

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.SearchView
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.OnPointAnnotationClickListener
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.ukdw.isaimobile.MainActivity
import com.ukdw.isaimobile.R
import com.ukdw.isaimobile.dto.LocationCarbon
import com.ukdw.isaimobile.services.Impl.LoadLocationCarbonServiceImpl
import com.ukdw.isaimobile.services.LoadLocationCarbonService


class HomeFragment : Fragment() {
    lateinit var activity: MainActivity
    private lateinit var mapView: MapView
    private lateinit var searchBar: SearchView
    private lateinit var listView: ListView
    private lateinit var searchClose: LinearLayout
    private var loadLocationCarbonService: LoadLocationCarbonService = object :
        LoadLocationCarbonServiceImpl(){}

    private var coordinateMap: Array<String> = arrayOf("January", "February", "March", "April")
    private lateinit var arrayAdapter: ArrayAdapter<String>
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        //Start Load Coordinate
        val carbonDatas = loadLocationCarbonService.loadData()
        val data: MutableList<Map<String, String>> = ArrayList()
        for (i in 0 until carbonDatas.size) {
            val datum: MutableMap<String, String> = HashMap(2)
            datum["title"] = carbonDatas[i].area
            datum["subtitle"] = carbonDatas[i].sampleCode
            data.add(datum)
        }
        //End Load


        //Start Setting map
        mapView=view.findViewById(R.id.mapView)
        mapView.getMapboxMap().loadStyleUri(
            Style.DARK
        ) {
            for(i in carbonDatas){
                addAnnotationToMap(i.longitude, i.latitude, view, i)
            }
//            addAnnotationToMap(110.481451, -7.741573, view)
//            addAnnotationToMap(110.481570, -7.741952, view)
//            addAnnotationToMap(110.479132, -7.766405, view)
//            addAnnotationToMap(110.478102, -7.767123, view)
        }


        val hiddenPanel = view.findViewById(R.id.detailLocation) as ViewGroup
        val detailButton = view.findViewById<LinearLayout>(R.id.bgDetailButton)
        val bottomDown: Animation = AnimationUtils.loadAnimation(
            context,
            R.anim.animation_bottom_down
        )
        detailButton.setOnClickListener {
            hiddenPanel.startAnimation(bottomDown)
            hiddenPanel.visibility = View.GONE
        }
        //End setting map



        //Start search
        searchClose = view.findViewById(R.id.searchClose)
        searchBar = view.findViewById(R.id.idSearch)
        listView = view.findViewById(R.id.idListView)

//        arrayAdapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, loadLocationCarbonService.loadData().map{ it.area;})

        val adapter = SimpleAdapter(
            activity, data,
            android.R.layout.simple_list_item_2, arrayOf("title", "subtitle"), intArrayOf(
                android.R.id.text1,
                android.R.id.text2
            )
        )
        listView.adapter = adapter

        listView.onItemClickListener = object: AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val cameraPosition = CameraOptions.Builder()
                    .center(Point.fromLngLat(carbonDatas[p2].longitude, carbonDatas[p2].latitude)).zoom(16.0)
                    .build()
                mapView.getMapboxMap().setCamera(cameraPosition)
            }

        }

        searchBar.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                listView.visibility = View.VISIBLE
                searchClose.visibility = View.VISIBLE
            }

        })

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                arrayAdapter.filter.filter(p0)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                listView.visibility = View.VISIBLE
                searchClose.visibility = View.VISIBLE
                arrayAdapter.filter.filter(p0)
                return false
            }
        })

        searchClose.setOnClickListener {
            searchClose.visibility = View.GONE
            listView.visibility = View.GONE
        }

        return view
    }

    private fun addAnnotationToMap(longitude: Double, latitude: Double, view: View, locationCarbon: LocationCarbon) {
        bitmapFromDrawableRes(
            activity,
            R.drawable.baseline_location_on_24
        )?.let {
            val annotationApi = mapView.annotations
            val pointAnnotationManager = annotationApi.createPointAnnotationManager(mapView)
            pointAnnotationManager.apply {
                addClickListener(
                    OnPointAnnotationClickListener {
                        val bottomUp: Animation = AnimationUtils.loadAnimation(
                            context,
                            R.anim.animation_bottom_up
                        )
                        (view.findViewById(R.id.sampleCode) as TextView).text = locationCarbon.sampleCode
                        (view.findViewById(R.id.landName) as TextView).text = locationCarbon.landName
                        (view.findViewById(R.id.commodity) as TextView).text = locationCarbon.commodity
                        (view.findViewById(R.id.samplingDate) as TextView).text = locationCarbon.samplingDate.toString()
                        (view.findViewById(R.id.soilCarbon) as TextView).text = locationCarbon.soilCarbon.toString()
                        (view.findViewById(R.id.plantCarbon) as TextView).text = locationCarbon.soilCarbon.toString()

                        val hiddenPanel = view.findViewById(R.id.detailLocation) as ViewGroup
                        hiddenPanel.startAnimation(bottomUp)
                        hiddenPanel.visibility = View.VISIBLE
                        false
                    }
                )
            }

            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude, latitude))
                .withIconImage(it)
            pointAnnotationManager.create(pointAnnotationOptions)
        }
    }
    private fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int) =
        convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))

    private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
        if (sourceDrawable == null) {
            return null
        }
        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
            val constantState = sourceDrawable.constantState ?: return null
            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth, drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }


}