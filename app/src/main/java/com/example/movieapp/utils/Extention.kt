package com.example.movieapp.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

fun View.makeVisible(visible :Boolean){
    if(visible){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.INVISIBLE
    }
}

fun RecyclerView.initRec(layout :LayoutManager, adapter :RecyclerView.Adapter<*>){
    this.layoutManager = layout
    this.adapter = adapter
}