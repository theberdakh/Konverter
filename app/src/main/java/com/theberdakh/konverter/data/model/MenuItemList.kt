package com.theberdakh.konverter.data.model

import com.theberdakh.konverter.R

object MenuItemList {

    fun getAllItems(): List<MenuItem>{
        return listOf(
            MenuItem(
                1,
                R.drawable.thermometer,
                "Temperatura"
            ),
            MenuItem(
                2,
                R.drawable.weight,
                "Awırlıq"
            ),
            MenuItem(
                3,
                R.drawable.length,
                "Uzınlıq"

            )
        )

    }
}