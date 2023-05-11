package com.theberdakh.konverter.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.applicationWindowToken, 0)
}

fun Double.toInteger(): Number {
    return if (this % 1 == 0.0) {
        this.toInt()
    } else this
}


fun String.checkSign(): Double {
    return if (this.isEmpty() && this.first() == '-') -0.0 else this.toDouble()
}

fun checkEditable(it: Editable?): Boolean {
    return it != null && it.isNotEmpty() && it.first() != '.'
}

fun Context.shareLink(string: String) {
    val sendIntent = Intent(
        Intent.ACTION_SEND
    ).apply {
        putExtra(Intent.EXTRA_TEXT, string)
        type = "text/plain"
    }


    val shareIntent = Intent.createChooser(
        sendIntent, null
    )

    startActivity(shareIntent)
}

