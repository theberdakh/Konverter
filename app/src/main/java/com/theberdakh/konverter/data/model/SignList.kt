package com.theberdakh.konverter.data.model

import com.theberdakh.konverter.util.Constants

object SignList {
    fun getSignList(id: Int): Array<String>{
        return when(id){
            1 -> arrayOf(Constants.CELSIUS, Constants.FAHRENHEIT, Constants.KELVIN)
            2 -> arrayOf(Constants.TON, Constants.KILOGRAM, Constants.GRAM)
            else -> arrayOf(Constants.KILOMETER, Constants.METER, Constants.CENTIMETER)

        }
    }
}

