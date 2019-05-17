package co.prueba.peiky.api


import android.os.Environment
import co.prueba.peiky.Utils.AdeptAndroid
import co.prueba.peiky.Utils.ApiConstans
import co.prueba.peiky.models.ResponseDetailMovie
import co.prueba.peiky.models.ResponseMovies
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File


interface WikiApiService {



    @GET("3/movie/{category}")
    fun popular(
            @Path("category") category: String, @Query("api_key") api_key: String, @Query("language") language: String, @Query("page") page: String): Observable<ResponseMovies>

    @GET("3/search/movie")
    fun search(
            @Query("api_key") api_key: String,@Query("query") query: String, @Query("language") language: String, @Query("page") page: String): Observable<ResponseMovies>

    @GET("3/movie/{id_movie}")
    fun getDetailMovie(
            @Path("id_movie") id_movie: String,
            @Query("api_key") api_key: String, @Query("language") language: String, @Query("append_to_response") append_to_response: String): Observable<ResponseDetailMovie>


    companion object {

        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(File(Environment.getExternalStorageDirectory().getAbsolutePath()), cacheSize)

        val gson = GsonBuilder()
                .setLenient()
                .create()

        val okHttpClient = OkHttpClient.Builder()
                .cache(myCache)
                .addInterceptor { chain ->
                    var request = chain.request()
                    request = if (AdeptAndroid.hasNetwork()!!)
                        request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                    else
                        request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                    chain.proceed(request)
                }
                .build()

        fun create(): WikiApiService {

            val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstans.URL_BASE)
                    //.client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            return retrofit.create(WikiApiService::class.java)
        }


    }
}