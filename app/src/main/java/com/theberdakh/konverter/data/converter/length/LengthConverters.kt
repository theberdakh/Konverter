package com.theberdakh.konverter.data.converter.length

import com.theberdakh.konverter.util.Constants

fun Double.toKilometer(from: String): Double {
    return when(from){
        Constants.CENTIMETER -> this / 100000.0
        Constants.METER -> this / 1000.0
        else -> this
    }
}

fun Double.toMeter(from: String): Double {
    return when(from){
        Constants.CENTIMETER -> this / 100.0
        Constants.KILOMETER -> this * 1000.0
        else -> this
    }
}

fun Double.toCentimeter(from: String): Double {
    return when(from){
        Constants.METER -> this * 100.0
        Constants.KILOMETER -> this * 100000.0
        else -> this
    }

}