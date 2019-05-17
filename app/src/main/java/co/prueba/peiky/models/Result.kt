package co.prueba.peiky.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int = 0
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("video")
    @Expose
    var video: Boolean = false
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double = 0.0
    @SerializedName("title")
    @Expose
    var title: String = ""
    @SerializedName("popularity")
    @Expose
    var popularity: Double = 0.0
    @SerializedName("poster_path")
    @Expose
    var posterPath: String = ""
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String = ""
    @SerializedName("original_title")
    @Expose
    var originalTitle: String = ""
    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int> = ArrayList()
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String = ""
    @SerializedName("adult")
    @Expose
    var adult: Boolean = false
    @SerializedName("overview")
    @Expose
    var overview: String = ""
    @SerializedName("release_date")
    @Expose
    var releaseDate: String = ""

}