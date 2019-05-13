package com.resourcefulbrain.useraccountmanagementdemo

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.async
import org.json.JSONArray
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var users:ListView
    private lateinit var Avatar:ImageView
    val userData = ArrayList<String>()
    var currIndex:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        root.backgroundColor = Color.BLUE

        users = findViewById<ListView>(R.id.UserList)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userData)
        users.adapter = adapter
        val Adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, userData)
        val executor = Executors.newScheduledThreadPool(4)
        val imageView = ImageView(this)
        imageView.layoutParams = LinearLayout.LayoutParams(160, 160) // value is in pixels
        Avatar = findViewById(R.id.avatar)
        doAsync {
            val result = URL("https://reqres.in/api/users").readText()
            uiThread {
                val welcome = Welcome.fromJson(result)
                for (jsonIndex in 0 until welcome!!.data.size) {
                    userData.add("First name: "+welcome!!.data[jsonIndex].firstName+"\n Last Name: "+welcome!!.data[jsonIndex].lastName)
                }
                users.adapter = Adapter

                users.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    val welcome = Welcome.fromJson(result)
                    Glide.with(Avatar).load(welcome!!.data[position].avatar).into(Avatar)
                }
            }
        }

    }
    fun onClickAddUser(v:View) {
        setContentView(R.layout.activity_add_user)
    }
}
