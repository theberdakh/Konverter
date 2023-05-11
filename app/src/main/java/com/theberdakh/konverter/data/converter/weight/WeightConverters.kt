package com.theberdakh.konverter.data.converter.weight

import com.theberdakh.konverter.util.Constants


fun Double.toKilogram(from: String): Double {
   return when(from){
        Constants.GRAM -> this / 1000.0
       Constants.TON -> this * 1000.0
       else -> this
    }
}

fun Double.toGram(from: String): Double {
    return when(from){
        Constants.KILOGRAM -> this * 1000.0
        Constants.TON -> this * 1000000
        else -> this
    }
}

fun Double.toTon(from: String): Double {
    return when(from){
        Constants.GRAM -> this / 1000000.0
        Constants.KILOGRAM -> this / 1000.0
        else -> this
    }
}