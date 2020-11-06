package com.example.billingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
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
        button_update.setOnClickListener{
            //UpdateValue()
        }
    }
    fun getData(barcode:String?)
    {
        val networkHelper=NetworkHelper(this)
        if(networkHelper.isNetworkConnected()) {
            val request = RetrofitBuilder.buildService()
Log.i("barcode clicked","$barcode")
            val call =request.getBarcodeItem(barcode)
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
                     /**  var responseData= response
                       Log.i("editresponse","$responseData")**/
                       var responseData= response.body()
                       Log.i("editresponse","$responseData")

                      // Log.i("id","$x")


                      // Log.i("editresponse","$responseData[0].id")
                       editText_id.text = Editable.Factory.getInstance().newEditable(responseData!!.results.get(0).id.toString())
                    editText_barcode.text=Editable.Factory.getInstance().newEditable(responseData!!.results.get(0).barcode.toString())
                       editText_particulars.text=Editable.Factory.getInstance().newEditable(responseData!!.results.get(0).particulars.toString())
                       editText_amount.text=Editable.Factory.getInstance().newEditable(responseData!!.results.get(0).amount.toString())
                       editText_rate.text=Editable.Factory.getInstance().newEditable(responseData!!.results.get(0).rate.toString())
                       editText_qty.text=Editable.Factory.getInstance().newEditable(responseData!!.results.get(0).qty.toString())

                    //   editText_id.text=responseData.results.get(0).id

                     /** editText_id.setText(Integer.toString(responseData!!.results.get(0).id))

                       editText_barcode.setText(responseData.results.get(0).barcode)
                       editText_amount.setText(responseData.results.get(0).amount.toInt())
                       editText_particulars.setText(responseData.results.get(0).particulars.toInt())
                       editText_qty.setText(responseData.results.get(0).qty)
                       editText_rate.setText(responseData.results.get(0).rate.toInt())
**/
                       //editText_amount.setText()




                   }
                }

                override fun onFailure(call: Call<Itemsresponse>, t: Throwable) {
                    Log.i("Editresponse","failure")
                }

            })



        }
        else
        {
            Toast.makeText(this,"NO Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }
    /**fun UpdateValue()
    {

    }**/

}