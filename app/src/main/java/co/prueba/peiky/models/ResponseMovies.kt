package co.prueba.peiky.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseMovies {

    @SerializedName("page")
    @Expose
    var page: Int = 0
    @SerializedName("total_results")
    @Expose
    var totalResults: Int = 0
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int = 0
    @SerializedName("results")
    @Expose
    var results: MutableList<Result> = ArrayList()

}