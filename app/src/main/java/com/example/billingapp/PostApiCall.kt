package com.example.billingapp

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_details.*
import kotlinx.android.synthetic.main.activity_put_api_call.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostApiCall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_put_api_call)
      sendData()
    }
    fun sendData()
    {
        var networkHelper=NetworkHelper(this)
        if(networkHelper.isNetworkConnected())
        {
            var dataToBesnt=PostData()
            dataToBesnt.barcode=editText_put_barcode.text.toString()
           // dataToBesnt.particulars=editText_particulars.text.toString()
            dataToBesnt.amount=editText_put_amount.text.toString().toDouble()
            dataToBesnt.qty=editText_put_qty.text.toString().toInt()
            dataToBesnt.rate=editText_put_rate.text.toString().toDouble()
        var retro=RetrofitBuilder.buildService()
            var call=retro.sendToServer(dataToBesnt)
        call.enqueue(object : Callback<PostData> {
            override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
                Toast.makeText(this@PostApiCall, "Record Inserted", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<PostData>, t: Throwable) {
                Toast.makeText(this@PostApiCall, "Record Cannot be Inserted", Toast.LENGTH_SHORT)
                    .show()
            }

        })

        }
        else
        {
            Toast.makeText(this@PostApiCall, "No Internet ", Toast.LENGTH_SHORT)
                .show()

        }
    }
}