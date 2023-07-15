package com.example.movieapp.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class DetailsResponse(
    @SerializedName("actors")
    var actors: String?, // Tim Robbins, Morgan Freeman, Bob Gunton, William Sadler
    @SerializedName("awards")
    var awards: String?, // Nominated for 7 Oscars. Another 19 wins & 30 nominations.
    @SerializedName("country")
    var country: String?, // USA
    @SerializedName("director")
    var director: String?, // Frank Darabont
    @SerializedName("genres")
    var genres: List<String?>?,
    @SerializedName("id")
    var id: Int?, // 1
    @SerializedName("images")
    var images: List<String?>?,
    @SerializedName("imdb_id")
    var imdbId: String?, // tt0111161
    @SerializedName("imdb_rating")
    var imdbRating: String?, // 9.3
    @SerializedName("imdb_votes")
    var imdbVotes: String?, // 1,738,596
    @SerializedName("metascore")
    var metascore: String?, // 80
    @SerializedName("plot")
    var plot: String?, // Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.
    @SerializedName("poster")
    var poster: String?, // http://moviesapi.ir/images/tt0111161_poster.jpg
    @SerializedName("rated")
    var rated: String?, // R
    @SerializedName("released")
    var released: String?, // 14 Oct 1994
    @SerializedName("runtime")
    var runtime: String?, // 142 min
    @SerializedName("title")
    var title: String?, // The Shawshank Redemption
    @SerializedName("type")
    var type: String?, // movie
    @SerializedName("writer")
    var writer: String?, // Stephen King (short story "Rita Hayworth and Shawshank Redemption"), Frank Darabont (screenplay)
    @SerializedName("year")
    var year: String? // 1994
) : Parcelable