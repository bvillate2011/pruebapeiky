package co.prueba.peiky

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

import kotlinx.android.synthetic.main.activity_detail_movie.*
import android.webkit.WebSettings

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import co.prueba.peiky.Utils.ApiConstans
import co.prueba.peiky.adapter.TrillerAdapter
import co.prueba.peiky.models.ResponseDetailMovie
import co.prueba.peiky.viewModels.ActivityDetailMovieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class ActivityDetailMovie : AppCompatActivity() {

    private val mActivityDetailMovieViewModel by lazy {
        ActivityDetailMovieViewModel.create(this)
    }

    val adapter = TrillerAdapter()
    var idMovie = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val extras = intent.extras
        if (extras != null) {
            idMovie = extras.getString("idMovie")
        }

        mActivityDetailMovieViewModel.init(idMovie, ApiConstans.KEY_API,ApiConstans.LANGUAGE)
        mActivityDetailMovieViewModel.nicePlaces().observe(this, object : Observer<ArrayList<ResponseDetailMovie>> {
            override  fun onChanged(@Nullable nicePlaces: ArrayList<ResponseDetailMovie>) {
                //mAdapter.notifyDataSetChanged()
                txtNombre.setText(mActivityDetailMovieViewModel.nicePlaces().getValue()!![0].originalTitle)

                txtDateDetail.setText(mActivityDetailMovieViewModel.formateDateFromstring("yyyy-MM-dd","dd, MMM yyyy",mActivityDetailMovieViewModel.nicePlaces().getValue()!![0].releaseDate))
                txtTagline.setText(mActivityDetailMovieViewModel.nicePlaces().getValue()!![0].tagline)
                txtRating.setText(mActivityDetailMovieViewModel.nicePlaces().getValue()!![0].voteAverage.toString())
                txtRuntime.setText(mActivityDetailMovieViewModel.nicePlaces().getValue()!![0].runtime.toString() + " "+getString(R.string.minutes))
                txtDetailOverview.setText(mActivityDetailMovieViewModel.nicePlaces().getValue()!![0].overview)
                Picasso.with(applicationContext).load("https://image.tmdb.org/t/p/w200"+mActivityDetailMovieViewModel.nicePlaces().getValue()!![0].posterPath).into(imgMovieDetail)


                wbTriller.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        return false
                    }
                }

                val webSettings = wbTriller.getSettings()
                webSettings.setJavaScriptEnabled(true)
                webSettings.setLoadWithOverviewMode(true)
                webSettings.setUseWideViewPort(true)

                val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
                lvTrillers.layoutManager = layoutManager
                lvTrillers.setAdapter(adapter)

                if(mActivityDetailMovieViewModel.nicePlaces().getValue()!![0].videos!!.results!!.size>0){
                    wbTriller.loadUrl("https://www.youtube.com/embed/" +mActivityDetailMovieViewModel.nicePlaces().getValue()!![0].videos!!.results!![0].key)
                    adapter.setNotes(mActivityDetailMovieViewModel.nicePlaces().getValue()!![0].videos!!.results!!)
                }



            }
        })
    }
}