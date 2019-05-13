package com.resourcefulbrain.useraccountmanagementdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var users:ListView
    val userData = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        users = findViewById<ListView>(R.id.UserList)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userData)
        users.adapter = adapter
        val Adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, userData)
        val executor = Executors.newScheduledThreadPool(4)
        doAsync {
            val result = URL("https://reqres.in/api/users").readText()
            uiThread {
                val welcome = Welcome.fromJson(result)
                for (jsonIndex in 0 until welcome!!.data.size) {
                    userData.add("First name: "+welcome!!.data[jsonIndex].firstName+"\n Last name:"+welcome!!.data[jsonIndex].lastName)
                }
                users.adapter = Adapter

            }
        }
    }
    fun onClickAddUser(v:View) {

    }

}
