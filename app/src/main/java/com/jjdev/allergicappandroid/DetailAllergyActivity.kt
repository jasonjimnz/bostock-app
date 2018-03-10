package com.jjdev.allergicappandroid

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import org.jetbrains.anko.contentView

class DetailAllergyActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_allergy)

        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#facd8a")))
        var allergy :String? = getIntent().getStringExtra("allergy")
        var btnAllergies = findViewById<Button>(R.id.button8) as Button
        actionBar.title = allergy
        btnAllergies.setOnClickListener {
            var allergies_intent = Intent(this, ExtraInfoActivity::class.java)
            startActivity(allergies_intent)
        }

        if (allergy == "Gluten"){
            contentView!!.background = getDrawable(R.drawable.gluten)
        } else if(allergy == "Huevo"){
            contentView!!.background = getDrawable(R.drawable.huevo)
        } else if (allergy == "Cacahuete") {
            contentView!!.background = getDrawable(R.drawable.cacahuete)
        } else if (allergy == "Lactosa") {
            contentView!!.background = getDrawable(R.drawable.lactosa)
        } else if (allergy == "Soja"){
            contentView!!.background = getDrawable(R.drawable.soja)
        }
    }
}
