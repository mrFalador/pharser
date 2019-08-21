package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import kotlin.collections.ArrayList
import okhttp3.*
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.ViewDebug


class MainActivity : AppCompatActivity() {
    lateinit var progress: ProgressBar
    lateinit var listView_details: ListView
    var arrayList_details:ArrayList<Model> = ArrayList()
    val client = OkHttpClient()

    lateinit var DBHelper : DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress = findViewById(R.id.progressBar)
        progress.visibility = View.GONE

        DBHelper = DBHelper(this)

        show_button.setOnClickListener {
            progress.visibility = View.VISIBLE
            listView_details = findViewById<ListView>(R.id.listView) as ListView
            run("https://financialmodelingprep.com/api/v3/stock/actives")
        }

        pharse_button.setOnClickListener {
            progress.visibility = View.VISIBLE
            tobd("https://financialmodelingprep.com/api/v3/stock/actives")
        }
    }


     fun run(url: String){
        progress.visibility = View.VISIBLE
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
               progress.visibility = View.GONE
            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body()!!.string()
                val json_contact: JSONObject = JSONObject(str_response)
                var jsonarray_info: JSONArray = json_contact.getJSONArray("mostActiveStock")
                var i:Int = 0
                var size:Int = jsonarray_info.length()
                arrayList_details = ArrayList();
                for (i in 0.. size-1){
                    var json_objectdetail:JSONObject=jsonarray_info.getJSONObject(i)
                    var model:Model= Model();
                    model.ticker = json_objectdetail.getString("ticker")
                    model.changes = json_objectdetail.getString("changes")
                    model.price = json_objectdetail.getString("price")
                    model.changesPercentage = json_objectdetail.getString("changesPercentage")
                    model.companyName = json_objectdetail.getString("companyName")
                    arrayList_details.add(model)
                }

                runOnUiThread{
                    //@Override
                    val obj_adapter: CustomAdapter
                    obj_adapter = CustomAdapter(applicationContext, arrayList_details)
                    listView_details.adapter = obj_adapter
                    progress.visibility = View.GONE
                }

            }
        })
    }

    fun tobd(url: String){
        progress.visibility = View.VISIBLE
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                progressBar.visibility = View.GONE
            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body()!!.string()
                val json_contact: JSONObject = JSONObject(str_response)
                var jsonarray_info: JSONArray = json_contact.getJSONArray("mostActiveStock")
                var i: Int = 0
                var size: Int = jsonarray_info.length()
                for (i in 0..size - 1) {
                    var json_objectdetail: JSONObject = jsonarray_info.getJSONObject(i)
                    var model: Model = Model();
                    model.companyName = json_objectdetail.getString("companyName")
                    model.price = json_objectdetail.getString("price")
                    var id = model.ticker
                    var company = model.companyName
                    var price = model.price
                    var result = DBHelper.insertCompany(CompanyModel(id = id, company = company, price = price))

                }
            }
        })
    }
}
