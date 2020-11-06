package com.example.billingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
   btn_get.setOnClickListener{
       startActivity(Intent(this,GetApiCall::class.java))
       finish()

   }
        btn_put.setOnClickListener{
            startActivity(Intent(this,PostApiCall::class.java))
            finish()
        }

    }


}