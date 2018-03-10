package com.jjdev.allergicappandroid

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle

class QRActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)

        actionBar.title = "Lector QR"
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#a9e6ff")))
    }
}
