package com.example.mcs_qualification.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mcs_qualification.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private var myMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener { x: View? ->
            finish()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        myMap = googleMap
        val sydney = LatLng(-34.0, 151.0)
        myMap!!.addMarker(MarkerOptions().position(sydney).title("Sydney"))
        myMap!!.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}