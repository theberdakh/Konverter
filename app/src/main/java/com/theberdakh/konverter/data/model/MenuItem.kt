package com.theberdakh.konverter.data.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuItem(
    val id: Int,
    @DrawableRes val image: Int,
    val title: String
): Parcelable