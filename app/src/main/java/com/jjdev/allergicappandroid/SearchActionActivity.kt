package com.jjdev.allergicappandroid

import android.app.Activity
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.common.api.GoogleApiClient

class SearchActionActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_action)
        val back_btn = findViewById<Button>(R.id.button3) as Button
        val search_btn = findViewById<Button>(R.id.button4) as Button
        val edit_text = findViewById<EditText>(R.id.editText) as EditText
        val lat : Double = 40.4514599
        val lon : Double = -3.7266352
        val user = "jason"
        search_btn.setOnClickListener {
            var map_intent = Intent(this, MapsActivity::class.java)
            map_intent.putExtra("lat", lat)
            map_intent.putExtra("lon", lon)
            map_intent.putExtra("user", user)
            startActivity(map_intent)
        }

    }



}
