package com.resourcefulbrain.useraccountmanagementdemo

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_add_user.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class AddUser : AppCompatActivity() {
    lateinit var FirstName:TextView
    lateinit var LastName:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        root_addUser.backgroundColor = Color.BLUE
        FirstName = findViewById(R.id.First_Name)
        LastName = findViewById(R.id.Last_Name)

    }
    fun onCLickSignUp(v:View) {
        doAsync {
            val result = URL("https://reqres.in/api/users").readText()
            uiThread {
                val welcome = Welcome.fromJson(result)
                for (jsonIndex in 0 until welcome!!.data.size) {
                    val registeredUsers:String = "First Name: "+welcome!!.data[jsonIndex].firstName+"Last Name: "+welcome!!.data[jsonIndex].lastName
                    val authenticateUser:String = "First Name: "+FirstName.text+"Last Name: "+LastName.text
                    if (registeredUsers != authenticateUser) {
                        //ADD USER TO SERVER

                    }
                    else{
                        //USER ALREADY EXISTS
                    }
                }
            }
        }
    }
}
