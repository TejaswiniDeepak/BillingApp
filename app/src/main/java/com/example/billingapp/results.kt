package com.example.billingapp

import java.io.Serializable

data class results (
        val id:Int,
        val barcode:String,
        val particulars:String,
        val qty:Int,
        val rate:Long,
        val amount:Long


):Serializable
