package com.jjdev.allergicappandroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.find
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.net.URL
import android.os.AsyncTask
import android.widget.Toast
import org.json.JSONObject


class LocationActivity : Activity() {
    var locationImage : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        var goBackBtn = findViewById<Button>(R.id.button5) as Button
        var locationName = findViewById<TextView>(R.id.textView5) as TextView
        var locationDistance = findViewById<TextView>(R.id.textView6) as TextView
        var locationIntolerance = findViewById<TextView>(R.id.textView7) as TextView
        var goHomeBtn = findViewById<Button>(R.id.button6) as Button
        locationImage = findViewById<ImageView>(R.id.imageView) as ImageView

        var json_text = JSONObject(getIntent().getStringExtra("json_info"))
        locationName.text = json_text!!.getString("name")
        locationDistance.text = "Estás a: ${json_text!!.getDouble("distance").toInt().toString()} metros"
        if (json_text!!.getDouble("total_intolerance").toInt() == 200){
            locationIntolerance.text = ""
        } else {
            locationIntolerance.text = "Tienes un: ${json_text!!.getDouble("total_intolerance").toInt().toString()} % de intolerancia"
        }
        DownLoadImageTask(locationImage!!).execute("http://lorempixel.com/1280/1024/city/")
        goBackBtn.setOnClickListener {
            val goBackIntent = Intent(this, MapsActivity::class.java)
            val lat = 40.4514599
            val lon = -3.7266352
            val user = "jason"
            goBackIntent.putExtra("lat", lat)
            goBackIntent.putExtra("lon", lon)
            goBackIntent.putExtra("user", user)
            startActivity(goBackIntent)
        }
        goHomeBtn.setOnClickListener {
            var home_intent = Intent(this, HomeActivity::class.java)
            startActivity(home_intent)
        }
    }

    private inner class DownLoadImageTask(internal var imageView: ImageView) : AsyncTask<String, Void, Bitmap>() {

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        override fun doInBackground(vararg urls: String): Bitmap? {
            val urlOfImage = urls[0]
            var logo: Bitmap? = null
            try {
                val `is` = URL(urlOfImage).openStream()
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(`is`)
            } catch (e: Exception) { // Catch the download exception
                e.printStackTrace()
            }

            return logo
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        override fun onPostExecute(result: Bitmap) {
            imageView.setImageBitmap(result)
        }
    }
}
