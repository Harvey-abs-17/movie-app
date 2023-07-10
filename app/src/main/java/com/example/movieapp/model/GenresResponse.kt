package com.example.movieapp.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

class GenresResponse : ArrayList<GenresResponse.GenresResponseItem>(){
    @Parcelize
    data class GenresResponseItem(
        @SerializedName("id")
        var id: Int?, // 1
        @SerializedName("name")
        var name: String? // Crime
    ) : Parcelable
}