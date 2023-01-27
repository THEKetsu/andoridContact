package fr.isen.bender.andoridcontact

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import fr.isen.bender.andoridcontact.databinding.ActivityMainBinding
import org.json.JSONObject
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.bender.andoridcontact.Model.Data
import fr.isen.bender.andoridcontact.Model.Results

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myCategoryAdapter : CAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FF9800")))
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.ListContact.layoutManager = layoutManager
        val intent = Intent(this, DetailActivity::class.java)
        binding.ListContact.adapter = CAdapter(arrayListOf()){
            intent.putExtra("detail", it)
            startActivity(intent)
        }
        loadDishesFromAPI()
    }
    private fun loadDishesFromAPI() {
        Volley.newRequestQueue(this)
        println("function load :")
        val url = "https://randomuser.me/api/?results=10&nat=fr "
        val jsonObject = JSONObject()
        jsonObject.put("","")
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, jsonObject, {
                Log.w("MaintActivity", "response: $it")
                handleAPI(it.toString())
            }, {error ->
                Log.w("MainActivity", "Error with the request: $error")
            }
        )
        Volley.newRequestQueue(this).add(jsonRequest)
    }


    private fun handleAPI(data : String){
        val Result = Gson().fromJson(data, Data::class.java)
        val newResults=Result.results
        val adapter = binding.ListContact.adapter as CAdapter
        adapter.refreshList(newResults)
    }
}