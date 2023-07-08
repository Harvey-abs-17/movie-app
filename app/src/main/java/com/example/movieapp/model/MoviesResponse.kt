package com.example.movieapp.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@Parcelize
data class MoviesResponse(
    @SerializedName("data")
    var `data`: List<Data?>?,
    @SerializedName("metadata")
    var metadata: Metadata?
) : Parcelable {


    @Parcelize
    data class Data(
        @SerializedName("country")
        var country: String?, // USA
        @SerializedName("genres")
        var genres: List<String?>?,
        @SerializedName("id")
        var id: Int?, // 1
        @SerializedName("images")
        var images: List<String?>?,
        @SerializedName("imdb_rating")
        var imdbRating: String?, // 9.3
        @SerializedName("poster")
        var poster: String?, // http://moviesapi.ir/images/tt0111161_poster.jpg
        @SerializedName("title")
        var title: String?, // The Shawshank Redemption
        @SerializedName("year")
        var year: String? // 1994
    ) : Parcelable {
        companion object {
            @JvmStatic
            @BindingAdapter("android:setSrc")
            fun bindImage( imageView: ImageView ,poster: String) {
                Picasso.get().load(poster).into(imageView)
            }
        }
    }


    @Parcelize
    data class Metadata(
        @SerializedName("current_page")
        var currentPage: String?, // 1
        @SerializedName("page_count")
        var pageCount: Int?, // 18
        @SerializedName("per_page")
        var perPage: Int?, // 10
        @SerializedName("total_count")
        var totalCount: Int? // 177
    ) : Parcelable
}