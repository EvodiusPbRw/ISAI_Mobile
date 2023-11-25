package com.ukdw.isaimobile.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Transformations.map
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
//import com.mapbox.mapboxsdk.Mapbox
//import com.mapbox.mapboxsdk.maps.MapView
//import com.mapbox.mapboxsdk.maps.MapboxMap
//import com.mapbox.mapboxsdk.maps.Style
import com.ukdw.isaimobile.MainActivity
import com.ukdw.isaimobile.R


class HomeFragment : Fragment() {
    lateinit var activity: MainActivity
    private lateinit var mapView: MapView
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        mapView=view.findViewById(R.id.mapView)
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS)
        return view
    }
}