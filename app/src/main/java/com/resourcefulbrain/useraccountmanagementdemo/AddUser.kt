package com.resourcefulbrain.useraccountmanagementdemo

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_user.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class AddUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        root_addUser.backgroundColor = Color.BLUE

        doAsync {
            val result = URL("https://reqres.in/api/users").readText()
            uiThread {
                
            }
        }
    }

}
