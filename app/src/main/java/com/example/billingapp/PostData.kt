package com.example.billingapp

import java.io.Serializable

 data class PostData(

         var barcode: String="null",
         var particulars:String="null",
         var qty:Int=0,
         var rate: Double=0.0,
         var amount: Double =0.0

):Serializable