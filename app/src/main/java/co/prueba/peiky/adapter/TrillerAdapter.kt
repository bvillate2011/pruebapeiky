package co.prueba.peiky.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.prueba.peiky.R
import co.prueba.peiky.models.ResultDetail
import kotlinx.android.synthetic.main.activity_detail_movie.*

class TrillerAdapter : RecyclerView.Adapter<TrillerAdapter.TrillerHolder>() {
    private var notes: List<ResultDetail> = ArrayList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrillerHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_triller, parent, false)
        return TrillerHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrillerHolder, position: Int) {
        val currentNote = notes[position]


        holder.wbTrillerList.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        }

        val webSettings = holder.wbTrillerList.getSettings()
        webSettings.setJavaScriptEnabled(true)
        webSettings.setLoadWithOverviewMode(true)
        webSettings.setUseWideViewPort(true)
        holder.wbTrillerList.loadUrl("https://www.youtube.com/embed/" +currentNote.key)
      
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes: List<ResultDetail>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    class TrillerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wbTrillerList: WebView
      
        var context : Context

        init {
            context = itemView.context
            wbTrillerList = itemView.findViewById(R.id.wbTrillerList)
        
        }
    }
}