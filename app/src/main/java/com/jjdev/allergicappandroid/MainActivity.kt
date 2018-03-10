package com.jjdev.allergicappandroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson

import com.github.kittinunf.fuel.httpGet


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button) as Button
        var user = findViewById<EditText>(R.id.editText) as EditText
        var pass = findViewById<EditText>(R.id.editText2) as EditText
        val back_button = findViewById<Button>(R.id.button2) as Button
        //FuelManager.instance.basePath = "http://localhost:3000"
        button.setOnClickListener {
            /*Fuel.get("http://10.0.2.2:3000/").responseJson{ request, response, result ->
                        //Log.v("response", result.get().content)
                        println(result.get().obj().get("msj")) // obj().get("parametro")
                    }*/
            //Fuel.post("http://10.0.2.2:3000/login").body("{ \"username\" : \"${user.text}\", \"password\": \"${pass.text}\" }").responseJson { request, response, result ->
            Fuel.post("http://10.0.2.2:3000/login", listOf("username" to user.text, "password" to pass.text)).responseJson { request, response, result ->
                Log.d("cUrl log", request.cUrlString())
                //println(response)
                val (bytes, error) = result
                if (bytes != null){
                    println(result.get().obj().get("status"))
                    Toast.makeText(this, "Your token is: ${result.get().obj().get("token")}", Toast.LENGTH_SHORT).show()
                    var loginOkIntent = Intent(this, HomeActivity::class.java)
                    loginOkIntent.putExtra("session_token", result.get().obj().get("token").toString())
                    startActivity(loginOkIntent)
                } else {
                    println("NON LOGGED")
                }
            }
            Toast.makeText(this, "User: ${user.text}, pass: ${pass.text}", Toast.LENGTH_SHORT).show()
        }
        back_button.setOnClickListener {
            val main_intent = Intent(this, HomeActivity::class.java)
            startActivity(main_intent)
        }
    }
}
