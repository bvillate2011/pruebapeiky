package co.prueba.peiky.viewModels

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import co.prueba.peiky.models.ResponseDetailMovie
import co.prueba.peiky.repository.NicePlaceRepository
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ActivityDetailMovieViewModel : ViewModel() {

    private var mDetailMovie: MutableLiveData<ArrayList<ResponseDetailMovie>>? = null
    private var mRepo: NicePlaceRepository? = null

    /**
     * metodo que contiene la instancia de MutableLivedata para ser manejado
     */
    fun nicePlaces(): LiveData<ArrayList<ResponseDetailMovie>> {
        return mDetailMovie!!
    }

    /**
     * transforma un determinado formato de fecha en uno deseado
     */
    fun formateDateFromstring(inputFormat: String, outputFormat: String, inputDate: String): String {

        var parsed: Date? = null
        var outputDate = ""

        val df_input = SimpleDateFormat(inputFormat, Locale.getDefault())
        val df_output = SimpleDateFormat(outputFormat, java.util.Locale.getDefault())

        try {
            parsed = df_input.parse(inputDate)
            outputDate = df_output.format(parsed)

        } catch (e: ParseException) {
        }

        return outputDate

    }


    /**
     * inicializador del repositorio y el objeto mutablelivedata
     */
    fun init(id_movie:String,api_key:String, laguage :String) {
        if (mDetailMovie != null) {
            return
        }
        mRepo = NicePlaceRepository.getInstance()
        mDetailMovie = mRepo!!.detailMovie(id_movie,api_key,laguage)
    }

    /**
     * inicializador del viewModelSegun los parametros del activity
     */
    companion object{
        fun create(activity: FragmentActivity): ActivityDetailMovieViewModel{
            var productDetailViewModel = ViewModelProviders.of(activity).get(ActivityDetailMovieViewModel::class.java)
            return productDetailViewModel
        }
    }

}