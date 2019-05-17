package co.prueba.peiky.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseDetailMovie {

    @SerializedName("adult")
    @Expose
    var adult: Boolean = false
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String = ""
    @SerializedName("budget")
    @Expose
    var budget: Int = 0
    @SerializedName("homepage")
    @Expose
    var homepage: String = ""
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("imdb_id")
    @Expose
    var imdbId: String = ""
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String = ""
    @SerializedName("original_title")
    @Expose
    var originalTitle: String = ""
    @SerializedName("overview")
    @Expose
    var overview: String = ""
    @SerializedName("popularity")
    @Expose
    var popularity: Double = 0.0
    @SerializedName("poster_path")
    @Expose
    var posterPath: String = ""
    @SerializedName("release_date")
    @Expose
    var releaseDate: String = ""
    @SerializedName("revenue")
    @Expose
    var revenue: Int = 0
    @SerializedName("runtime")
    @Expose
    var runtime: Int = 0
    @SerializedName("status")
    @Expose
    var status: String = ""
    @SerializedName("tagline")
    @Expose
    var tagline: String = ""
    @SerializedName("title")
    @Expose
    var title: String = ""
    @SerializedName("video")
    @Expose
    var video: Boolean = false
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double = 0.0
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int = 0
    @SerializedName("videos")
    @Expose
    var videos: Videos? = null
    @SerializedName("recommendations")
    @Expose
    var recommendations: Recommendations? = null

}