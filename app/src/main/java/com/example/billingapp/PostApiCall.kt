package com.example.billingapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_edit_details.*
import kotlinx.android.synthetic.main.activity_put_api_call.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostApiCall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_put_api_call)
        btn_barcode.setOnClickListener{

            scanBarcode()

        }
        button_update_Post.setOnClickListener {
            if(editText_put_particulars.text!!.isEmpty()||editText_put_particulars.text!!.isEmpty()||
                    editText_put_amount.text!!.isEmpty()||editText_put_qty.text!!.isEmpty()||
                    editText_put_rate.text!!.isEmpty(
                    ))
            {
                Toast.makeText(this,"Please ENter all the details",Toast.LENGTH_SHORT).show()

            }
            else {
                sendData()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
        button_close_Post.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }


    }
    fun sendData()
    {
        var networkHelper=NetworkHelper(this)
        if(networkHelper.isNetworkConnected())
        {
            var dataToBesnt=PostData()




            dataToBesnt.barcode=editText_put_barcode.text.toString()

           dataToBesnt.particulars= editText_put_particulars.text.toString()
           // dataToBesnt.amount=editText_put_amount.text.toString().toDouble()

            dataToBesnt.qty=editText_put_qty.text.toString().toInt()
            var quantity=editText_put_qty.text.toString().toInt()
            var rate=editText_put_rate.text.toString().toDouble()

            dataToBesnt.amount=quantity*rate
            //Editable.Factory.getInstance().newEditable(result.contents)
           editText_put_amount.text=Editable.Factory.getInstance().newEditable((quantity*rate).toString())
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
    private fun scanBarcode() {

            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setBeepEnabled(false)
            intentIntegrator.setCameraId(0)
            intentIntegrator.setPrompt("SCAN")
            intentIntegrator.setBarcodeImageEnabled(false)
            intentIntegrator.initiateScan()

    }

       override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
        {
            val result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
            if(result!=null)
            {
                    if(result.contents==null)
                    {
                        Toast.makeText(this,"SCanning Cancelled",Toast.LENGTH_SHORT).show()
                    }
                else
                    {
                        Toast.makeText(this,"Scanning in progress",Toast.LENGTH_SHORT).show()
                        editText_put_barcode.visibility=View.VISIBLE
                        layout_editText_put_barcode.visibility=View.VISIBLE
                        btn_barcode.visibility=View.GONE
                        editText_put_barcode.text=Editable.Factory.getInstance().newEditable(result.contents)

                    }
            }
            else
            {
                super.onActivityResult(requestCode,resultCode,data)
            }
        }

}