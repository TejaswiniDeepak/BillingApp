package com.example.billingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_details)
        var data: String? =intent.getStringExtra("BARCODE")
        getData(data)
    }
    fun getData(barcode:String?)
    {
        val networkHelper=NetworkHelper(this)
        if(networkHelper.isNetworkConnected()) {
            val request = RetrofitBuilder.buildService()
Log.i("barcode clicked","$barcode")
            val call =request.getBarcodeItem(barcode!!)
            call!!.enqueue(object : Callback<Itemsresponse>
            {
                override fun onResponse(
                    call: Call<Itemsresponse>,
                    response: Response<Itemsresponse>
                ) {
                   if(response.isSuccessful&& response.body()!=null)
                   {
                        progressBar2.visibility=View.GONE
                       linearLayout.setVisibility(View.VISIBLE)
                       var responseData= response.body()!!
                       Log.i("editresponse","$responseData")

                   }
                }

                override fun onFailure(call: Call<Itemsresponse>, t: Throwable) {
                    Log.i("Editresponse","failure")
                }

            })



        }
        else
        {
            Toast.makeText(this,"NO Interenet Connection", Toast.LENGTH_SHORT).show()
        }
    }
}