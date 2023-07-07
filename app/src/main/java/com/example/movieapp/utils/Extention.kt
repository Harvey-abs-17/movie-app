package com.example.movieapp.utils

import android.view.View

fun View.makeVisible(visible :Boolean){
    if(visible){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.INVISIBLE
    }
}