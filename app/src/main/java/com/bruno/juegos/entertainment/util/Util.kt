package com.bruno.juegos.entertainment.util

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bruno.juegos.entertainment.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackBar(view: View, results : Boolean){
    var snackbar : Snackbar? = null

    snackbar = if(results){
        Snackbar.make(view, getString(R.string.splash_successfull_retry),
            Snackbar.LENGTH_SHORT).setAction("Action", null)
    }else{
        Snackbar.make(view, getString(R.string.splash_error_retry),
            Snackbar.LENGTH_SHORT).setAction("Action", null)
    }

    snackbar.setActionTextColor(Color.BLUE)
    val snackbarView = snackbar.view
    snackbarView.setBackgroundColor(Color.LTGRAY)
    val textView =
        snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.setTextColor(Color.BLUE)
    textView.textSize = 28f
    snackbar.show()
}