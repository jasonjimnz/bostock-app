package com.jjdev.allergicappandroid

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button

class ManagementActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management)

        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#79c471")))
        actionBar.title = "Gestión de usuario"

        var homeBtn = findViewById<Button>(R.id.button7) as Button
        homeBtn.setOnClickListener {
            var home_intent = Intent(this, HomeActivity::class.java)
            startActivity(home_intent)
        }
    }
}
