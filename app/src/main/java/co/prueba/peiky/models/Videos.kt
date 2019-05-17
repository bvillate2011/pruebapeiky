package co.prueba.peiky.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Videos {

    @SerializedName("results")
    @Expose
    var results: MutableList<ResultDetail> = ArrayList()

}