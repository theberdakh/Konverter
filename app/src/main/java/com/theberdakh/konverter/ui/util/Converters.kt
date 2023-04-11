package com.theberdakh.konverter.ui.util

fun Double.toFahrenheit(from: String): Double {
    return when (from) {
        Constants.CELSIUS -> this * (9 / 5.0) + 32
        Constants.KELVIN -> (this - 273.15) * (9 / 5.0) + 32
        else -> this
    }
}

fun Double.toCelsius(from: String): Double {
    return when (from) {
        Constants.FAHRENHEIT -> (this - 32) * 5.0 / 9
        Constants.KELVIN -> this - 273.15
        else -> this
    }
}

fun Double.toKelvin(from: String): Double {
    return when (from) {
        Constants.CELSIUS -> this + 273.15
        Constants.FAHRENHEIT -> (1 - 32) * (5.0 / 9) + 273.15
        else -> this
    }
}

fun Double.toInteger(): Number {
    return if (this % 1 == 0.0) {
        this.toInt()
    } else this
}

fun String.checkSign(): Double {
    return if (this.length==1 && this.first() == '-') -0.0 else this.toDouble()
}
