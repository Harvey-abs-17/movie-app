package com.example.movieapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRegisterBody(
    @SerializedName("name")
    var name :String = "",
    @SerializedName("email")
    var email :String = "",
    @SerializedName("password")
    var password :String = ""
) :Parcelable
