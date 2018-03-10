package com.jjdev.allergicappandroid

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.ListView
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONArray
import org.json.JSONObject
import java.nio.DoubleBuffer




class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    var lat: Double? = null
    var lon: Double? = null
    var user: String? = null
    /*
        Esta cosa es porque para mandar parámetros por los Intents
        hay que dar un valor por defecto y las latitudes y longitudes
        pueden tener valores 0 o negativos
     */
    var xtrangeValue : Double = 10000000.0000000000001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        actionBar.title = "Localización"
        actionBar.setBackgroundDrawable(ColorDrawable(Color.rgb(252,202,31)))
        lat = getIntent().getDoubleExtra("lat", xtrangeValue)
        lon = getIntent().getDoubleExtra("lon", xtrangeValue)
        user = getIntent().getStringExtra("user")
        println("USUARIO: ${user}")
        if (lat != null && lon != null && user != null){
            var url1 = "http://10.0.2.2:3000/search/locations"
            if (user == "none"){
                url1 = "http://10.0.2.2:3000/search/locations/unsigned"
            }
            Fuel.post(url1, listOf("lat" to lat, "lon" to lon, "user" to user)).responseJson(){ request, response, result ->
                val (data, error) = result
                if (error == null) {
                    var location = result.get().obj().getJSONArray("results") as JSONArray// obj().get("parametro")
                    println(location)
                    for (i in 0..(location.length() - 1)){
                        var location_test = location[i] as JSONObject
                        //Toast.makeText(this, "Cosa: ${location_test.get("name")}", Toast.LENGTH_SHORT).show()
                        mMap!!.addMarker(MarkerOptions().snippet(location_test.toString()).title(location_test.getString("name")).position(LatLng(location_test.getDouble("latitude"), location_test.getDouble("longitude"))))
                    }
                    mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom( LatLng(lat!!, lon!!), 12.0f))
                }
            }
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney, Australia, and move the camera.
        val sydney = LatLng(40.4167750,-3.7037900 )
        //mMap!!.addMarker(MarkerOptions().position(sydney).title("Marker in Madrid"))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap!!.setOnMarkerClickListener { marker ->
            //Toast.makeText(this, marker.title, Toast.LENGTH_SHORT).show()// display toast
            var location_intent = Intent(this, LocationActivity::class.java)
            location_intent.putExtra("name", marker.title)
            location_intent.putExtra("json_info", marker.snippet)
            //Toast.makeText(this, marker.snippet, Toast.LENGTH_SHORT).show()
            startActivity(location_intent)
            /*if (marker.title.equals("MyHome"))
            // if marker source is clicked
                Toast.makeText(this, marker.title, Toast.LENGTH_SHORT).show()// display toast*/
            true
        }
    }


}