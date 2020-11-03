package com.example.billingapp

import java.io.Serializable

data class Itemsresponse (
        //val results:List<Items>

       /** val barcode : Long,
        val particulars:String,
        val qty:Int,
        val rate:Float,
        val amount:Long**/

        val code:Int,
    val status:String,
    val message: String,
val results:List<results>

):Serializable