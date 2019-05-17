package co.prueba.peiky.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultDetail {

    @SerializedName("id")
    @Expose
    var id: String = ""
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String = ""
    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String = ""
    @SerializedName("key")
    @Expose
    var key: String = ""
    @SerializedName("name")
    @Expose
    var name: String = ""
    @SerializedName("site")
    @Expose
    var site: String = ""
    @SerializedName("size")
    @Expose
    var size: Int = 0
    @SerializedName("type")
    @Expose
    var type: String = ""

}