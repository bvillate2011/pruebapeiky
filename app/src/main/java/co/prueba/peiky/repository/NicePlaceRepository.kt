package co.prueba.peiky.repository


import android.util.Log
import androidx.lifecycle.MutableLiveData
import co.prueba.peiky.Utils.ApiConstans
import co.prueba.peiky.api.WikiApiService
import co.prueba.peiky.models.ResponseDetailMovie
import co.prueba.peiky.models.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class NicePlaceRepository {
    private val dataSet = ArrayList<Result>()
    val data = MutableLiveData<ArrayList<Result>>()

    private val dataSetDm = ArrayList<ResponseDetailMovie>()
    val dataDm = MutableLiveData<ArrayList<ResponseDetailMovie>>()


    private val wikiApiServe by lazy {
        WikiApiService.create()
    }

    private var disposableCompanies: Disposable? = null
    private var disposableDetailMovie: Disposable? = null


    /**
     * llamado inicial al api por la categoria popular
     */
    fun nicePlaces(): MutableLiveData<ArrayList<Result>>
      {


          disposableCompanies = wikiApiServe.popular("popular",ApiConstans.KEY_API, "en-US","1")
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(
                  { result -> run {

                      dataSet.addAll(result.results)
                      data.setValue(dataSet)
                  }
                  },
                  { error -> run{

                      Log.v("disposableCompanies","error")
                  } }
              )
          return data
      }

    /**
     * llamado que se ejecuta segun cada categoria y numero de pagina para traer mas resultados
     */

    fun moreData(category:String,api_key:String,language:String, page :String): MutableLiveData<ArrayList<Result>>
    {

        //val data = MutableLiveData<ArrayList<Result>>()
        disposableCompanies = wikiApiServe.popular(category,api_key, language,page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> run {

                    dataSet.addAll(result.results)
                    data.postValue(dataSet)
                }
                },
                { error -> run{

                    Log.v("disposableCompanies","error")

                } }
            )
        return data
    }
    /**
     * llamado que se ejecuta segun cada categoria y numero de pagina para traer mas resultados
     */

    fun search(query:String,api_key:String,language:String, page :String): MutableLiveData<ArrayList<Result>>
    {

        //val data = MutableLiveData<ArrayList<Result>>()
        disposableCompanies = wikiApiServe.search(api_key, query,language,page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> run {
                    dataSet.clear()
                    dataSet.addAll(result.results)
                    data.postValue(dataSet)
                }
                },
                { error -> run{

                    Log.v("disposableCompanies","error")

                } }
            )
        return data
    }

    /**
     * llamado en el cual se cambia de categoria para mostrar los respectivos resultados
     */
    fun otherCategory(category:String,api_key:String,language:String, page :String): MutableLiveData<ArrayList<Result>>
    {

        //val data = MutableLiveData<ArrayList<Result>>()
        disposableCompanies = wikiApiServe.popular(category,api_key, language,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> run {
                            dataSet.clear()
                            dataSet.addAll(result.results)
                            data.postValue(dataSet)
                        }
                        },
                        { error -> run{

                            Log.v("disposableCompanies","error")
                        } }
                )
        return data
    }

    /**
     * llamado que muestra el detalle de una pelicula
     */
    fun detailMovie(id_movie:String,api_key:String, laguage :String): MutableLiveData<ArrayList<ResponseDetailMovie>>
    {

        //val data = MutableLiveData<ArrayList<DataCompaniesMenu>>()
        disposableDetailMovie = wikiApiServe.getDetailMovie(id_movie, api_key,laguage,"videos,recommendations")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> run {
                            dataSetDm.clear()
                            dataSetDm.addAll(listOf(result))
                            dataDm.postValue(dataSetDm)
                        }
                        },
                        { error -> run{

                            Log.v("disposableCompanies","error")
                            /* if (util.isOnline(applicationContext)){
                                 var net =  NetworkError(error.cause);
                                 util.crearToastGenerico(applicationContext,net.getAppErrorMessage())
                             }else{
                                 dialogNOinter!!.show()
                                 //util.crearToastGenerico(applicationContext,getString(R.string.no_internet))
                                 //Log.e("Error:",net.getAppErrorMessage());
                             }*/
                        } }
                )
        return dataDm
    }


    companion object {

        private var instance: NicePlaceRepository? = null

        fun getInstance(): NicePlaceRepository {
            if (instance == null) {
                instance = NicePlaceRepository()
            }
            return instance as NicePlaceRepository
        }
    }
}