package com.example.eko.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.eko.R
import com.example.eko.data.model.db.MapLocationEntity
import com.example.eko.ui.viewmodes.MapViewModel
import com.example.eko.utils.Exp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MapFragment : Fragment() {

    private val viewModel: MapViewModel by viewModels()
    var mapFragment: SupportMapFragment? = null
    private var googleMap: GoogleMap? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_map, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMap()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getAll.collectLatest {
                if (it.isNotEmpty())
                    addMarkerMap(it)
                Log.i("onViewCreated", "onViewCreated: " + it.size)

            }
        }
    }

    private fun initMap() {
        mapFragment =
            childFragmentManager.findFragmentById(R.id.frg) as SupportMapFragment?

        mapFragment?.getMapAsync { mMap ->
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            mMap.clear() //clear old markers
            val googlePlex = CameraPosition.builder()
                .target(LatLng(36.292132565221934, 59.588824507503226))
                .zoom(11f)
                .bearing(0f)
                .tilt(45f)
                .build()
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null)
            googleMap = mMap
        }
    }

//    36.292132565221934, 59.588824507503226

    fun addMarkerMap(mapModels: List<MapLocationEntity>) {

        for (mapModel in mapModels) {
            val marker = MarkerOptions()
                .position(
                    LatLng(
                        mapModel.latLoc,
                        mapModel.longLoc
                    )
                )
                .title(mapModel.name)
                .snippet(Exp.convertLongToCalenderString(mapModel.date))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))

            googleMap?.addMarker(marker)
        }

    }

}