package com.example.billingapp

//import android.telecom.Call

import retrofit2.Call
import retrofit2.http.*


interface ItemsService {
    @GET("extra/barcodelist")
    fun getItems(): Call<Itemsresponse>
    @GET("extra/barcodelist/{barcode}")
    fun getBarcodeItem(@Path("barcode") barcode:String?):Call<Itemsresponse>

 //   @GET("extra/barcodelist/{barcode}")
   // fun groupList(@Path("id") barcode: String?): Call<Itemsresponse>

   // @Headers("Content-Type: application/json")
    @POST("extra/extra_particulars")
    fun sendToServer(@Body newData: PostData):Call<PostData>



}

/**val barcode: String,
val particulars:String,
val qty:Int,
val rate: Double,
val amount:Long**/