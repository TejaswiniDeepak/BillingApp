package com.example.billingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_get_api_call.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetApiCall : AppCompatActivity(),CellClickListener {
    lateinit var BARCODE:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_api_call)

        fetchData()
    }
    private fun fetchData() {

        val networkHelper=NetworkHelper(this)
        if(networkHelper.isNetworkConnected())
        {
            val request=RetrofitBuilder.buildService()
            val call=request.getItems()

            call.enqueue(object : Callback<Itemsresponse> {
                override fun onResponse(call: Call<Itemsresponse>, response: Response<Itemsresponse>) {


                    //var weatherResponseJSONString= Gson().toJson(weatherList)

                    /**var responseInString=Gson().toJson(response.body())
                    Log.i("responseString","$responseInString")**/
                    if (response.isSuccessful && response.body() != null) {
                        progressBar.visibility=View.GONE
                        val ResponseFromServer = response.body()!!
                        recycler.setHasFixedSize(true)
                        recycler.itemAnimator=DefaultItemAnimator()
                        recycler.addItemDecoration(DividerItemDecoration(this@GetApiCall,1))
                        recycler.adapter=Adapter(ResponseFromServer.results,this@GetApiCall)



                        val movies = ResponseFromServer.code
                        val status = ResponseFromServer.status
                        Log.i("status is", "$status")
                        Log.i("result is ", "$movies")
                        val message = ResponseFromServer.message
                        Log.i("message", "$message")
                        val resultdata = ResponseFromServer.results
                        Log.i("result", "$resultdata")
                    } else {

                        Log.i("error1", "Error in response")
                    }
                    /** if(response.isSuccessful)
                    {
                    Log.i("response","successful response")
                    }
                    else if(response.body()==null)
                    {
                    Log.i("response","response body is null")
                    }
                    else
                    {
                    Log.i("response","other error in response")
                    }**/


                }

                override fun onFailure(call: Call<Itemsresponse>, t: Throwable) {
                    Log.i("onSOmeException", "$t.message")
                }


            })
            /**var newData=PostData("987654", "bike", 2, 2.32, 12546)
            /**val gson = Gson()
            val jsonString = Gson().fromJson(newData,PostData::class.java)**/
            val callPost=request.postitems(newData)
            callPost.enqueue(object : Callback<PostData> {
            override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
            val dataInserted = response.body()
            Log.i("post", "Data inserted $dataInserted")
            }

            override fun onFailure(call: Call<PostData>, t: Throwable) {
            Log.i("post", "$t")
            }

            })**/

        }
        else
        {
            Log.i("error2", "NoInternet")
        }
    }

    override fun onCellClickListener(data: String) {


        val intent=Intent(this,EditDetails::class.java)
        intent.putExtra("BARCODE",data)

        startActivity(intent)

        finish()
    }

}