package com.example.movieapp.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.picasso.Picasso

@Entity
data class FavoriteEntity(
    @PrimaryKey
    var id: Int = 0,
    var movie_name: String = "",
    var movie_poster: String = ""
){

    companion object{
        @JvmStatic
        @BindingAdapter("android:setSrc")
        fun bindImage(imageView: ImageView, poster: String) {
            Picasso.get().load(poster).into(imageView)
        }
    }
}
