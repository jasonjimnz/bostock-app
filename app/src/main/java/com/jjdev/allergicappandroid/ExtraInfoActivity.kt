package com.jjdev.allergicappandroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet


class ExtraInfoActivity : Activity() {
    private var listExtraInfo = ArrayList<Allergy>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extra_info)
        actionBar.title = "Más Información"
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#facd8a")))

        FuelManager.instance.basePath = "http://10.0.2.2:3000"
        "/allergy/list".httpGet().responseJson() { request, response, result ->
            //make a GET to http://httpbin.org/get and do something with response
            val (data, error) = result
            if (error == null) {
                var alllergies = result.get().obj().getJSONArray("allergies") // obj().get("parametro")
                for (i in 0..(alllergies.length() - 1)){
                    println(alllergies[i])
                    var allergy: String = alllergies[i] as String
                    listExtraInfo.add(Allergy(allergy))
                }
                var allergyAdapter = AllergyAdapter(this, listExtraInfo)
                var list = findViewById<ListView>(R.id.allergiesListAllergy) as ListView
                list.adapter = allergyAdapter
                list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    //Toast.makeText(this, listExtraInfo[position].title, Toast.LENGTH_SHORT).show()
                    var allergias = listExtraInfo[position].title.toString()
                    if (allergias == "Gluten" || allergias == "Huevo" || allergias == "Cacahuete" || allergias == "Lactosa" || allergias == "Soja"){
                        var detailIntent = Intent(this, DetailAllergyActivity::class.java)
                        detailIntent.putExtra("allergy", allergias)
                        startActivity(detailIntent)
                    }
                }
            }
        }
    }


    inner class AllergyAdapter : BaseAdapter {
        private var context: Context? = null
        private var allergyList = ArrayList<Allergy>()

        constructor(context: Context, allergyList: ArrayList<Allergy>){
            this.allergyList = allergyList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.allergy, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("JSA", "Tag for allergy: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            vh.allergyTitle.text = allergyList[position].title

            return view
        }

        override fun getItem(position: Int): Any {
            return allergyList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return allergyList.size
        }

        private inner class ViewHolder(view: View?) {
            val allergyTitle: TextView

            init {
                this.allergyTitle = view?.findViewById<TextView>(R.id.allergyItem) as TextView
            }
        }
    }

    class Allergy {
        var title: String? = null

        constructor(title: String){
            this.title = title
        }
    }
}
