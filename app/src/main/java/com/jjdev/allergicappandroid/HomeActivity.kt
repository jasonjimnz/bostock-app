package com.jjdev.allergicappandroid

import android.app.Activity
import android.content.Intent
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class HomeActivity : Activity() {
    var token:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btn_enciclopedia = findViewById<ImageButton>(R.id.boton_enciclopedia) as ImageButton
        val btn_localizacion = findViewById<ImageButton>(R.id.boton_localizacion) as ImageButton
        val btn_gestion = findViewById<ImageButton>(R.id.boton_gestion_usuario) as ImageButton
        val btn_qr = findViewById<ImageButton>(R.id.boton_qr) as ImageButton
        val btn_info_extra = findViewById<ImageButton>(R.id.boton_info_extra) as ImageButton
        val btn_login = findViewById<Button>(R.id.boton_login) as Button

        try {
            token = getIntent().getStringExtra("session_token")
            //Toast.makeText(this, "Token de session: ${token}", Toast.LENGTH_SHORT).show()
        } catch (e:Exception){
            println("No token")
        }


        btn_enciclopedia.setOnClickListener {
            //Toast.makeText(this, "Botón enciclopedia", Toast.LENGTH_SHORT).show()
            var wiki_activity = Intent(this, MapsActivity::class.java)
            if (token != null){
                wiki_activity.putExtra("session_token", token)
            }
            val lat : Double = 40.4514599
            val lon : Double = -3.7266352
            wiki_activity.putExtra("lat", lat)
            wiki_activity.putExtra("lon", lon)
            wiki_activity.putExtra("user", "none")

            startActivity(wiki_activity)
        }
        btn_localizacion.setOnClickListener {
            //Toast.makeText(this, "Botón localización", Toast.LENGTH_SHORT).show()
            val mapsIntent = Intent(this, MapsActivity::class.java)
            if (token != null){
                mapsIntent.putExtra("session_token", token)
            }
            val lat : Double = 40.4514599
            val lon : Double = -3.7266352
            val user = "jason"
            mapsIntent.putExtra("lat", lat)
            mapsIntent.putExtra("lon", lon)
            mapsIntent.putExtra("user", user)

            startActivity(mapsIntent)
        }
        btn_gestion.setOnClickListener {
            //Toast.makeText(this, "Botón gestión", Toast.LENGTH_SHORT).show()
            val management_intent = Intent(this, ManagementActivity::class.java)
            if (token != null){
                management_intent.putExtra("session_token", token)
            }
            startActivity(management_intent)
        }
        btn_qr.setOnClickListener {
            Toast.makeText(this, "Botón QR próximamente", Toast.LENGTH_SHORT).show()
            /*var qr_intent = Intent(this, QRActivity::class.java)
            if (token != null){
                qr_intent.putExtra("session_token", token)
            }
            startActivity(qr_intent)*/
        }
        btn_info_extra.setOnClickListener {
            //Toast.makeText(this, "Botón info extra", Toast.LENGTH_SHORT).show()
            var extra_intent = Intent(this, ExtraInfoActivity::class.java)
            if (token != null){
                extra_intent.putExtra("session_token", token)
            }
            startActivity(extra_intent)
        }
        btn_login.setOnClickListener {
            val aIntent = Intent(this, MainActivity::class.java)
            startActivity(aIntent)
        }
    }
}
