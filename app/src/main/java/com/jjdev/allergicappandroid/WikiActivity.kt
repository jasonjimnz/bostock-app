package com.jjdev.allergicappandroid

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toolbar

class WikiActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wiki)
        //setActionBar(R.menu.menu_action_bar)

        actionBar.title = "Enciclopedia"
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#3fbcef")))
    }
}
