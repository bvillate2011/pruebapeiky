package co.prueba.peiky.viewModels

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import co.prueba.peiky.models.Result
import co.prueba.peiky.repository.NicePlaceRepository


class MainActivityViewModel : ViewModel() {

    private var mNicePlaces: MutableLiveData<ArrayList<Result>>? = null
    private var mRepo: NicePlaceRepository? = null

    /**
     * por medio de este metodo sabemos que algo sucedio con la data
     */
    fun nicePlaces(): LiveData<ArrayList<Result>>{
        return mNicePlaces!!
    }

    /**
     * inicializador de las instancias de repo para llamar los servicios y del MutableLiveData
     */
    fun init() {
        if (mNicePlaces != null) {
            return
        }
        mRepo = NicePlaceRepository.getInstance()
        mNicePlaces = mRepo!!.nicePlaces()
    }

    /**
     * metodo que hace el llamado de paginacion
     */
    fun more(category:String,token:String,uid:String, offset :String) {
        mRepo!!.moreData(category,token,uid,offset)
    }
    /**
     * metodo que hace el llamado de paginacion
     */
    fun search(category:String,token:String,uid:String, offset :String) {
        mRepo!!.search(category,token,uid,offset)
    }

    /**
     * metodo que hace el llamado segun categorias
     */
    fun changeCategory(category:String,token:String,uid:String, offset :String) {
        mRepo!!.otherCategory(category,token,uid,offset)
    }



    companion object{
        fun create(activity: FragmentActivity): MainActivityViewModel{
            var productDetailViewModel = ViewModelProviders.of(activity).get(MainActivityViewModel::class.java)
            return productDetailViewModel
        }
    }

}