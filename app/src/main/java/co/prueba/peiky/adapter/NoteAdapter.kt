package co.prueba.peiky.adapter


import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import co.prueba.peiky.ActivityDetailMovie
import co.prueba.peiky.models.Result
import co.prueba.peiky.R
import com.squareup.picasso.Picasso


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    private var notes: List<Result> = ArrayList()

    lateinit private var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        context = parent.context
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = notes[position]
        holder.textViewTitle.setText(currentNote.title)
        holder.textViewDescription.setText(currentNote.releaseDate)
        holder.textViewPriority.setText(currentNote.voteAverage.toString())
        Picasso.with(context).load("https://image.tmdb.org/t/p/w200"+currentNote.posterPath).into(holder.imgMovie)

        holder.relRootProduct.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(holder.context, ActivityDetailMovie::class.java)
                intent.putExtra("idMovie",currentNote.id.toString());
                holder.context.startActivity(intent)
            }
        })
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes: List<Result>) {
        this.notes = notes
        notifyDataSetChanged()
    }

     class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val textViewTitle: TextView
         val textViewDescription: TextView
         val textViewPriority: TextView
         val imgMovie: ImageView
         val relRootProduct: RelativeLayout
         var context : Context

        init {
            context = itemView.context
            textViewTitle = itemView.findViewById(R.id.text_view_title)
            textViewDescription = itemView.findViewById(R.id.text_view_description)
            textViewPriority = itemView.findViewById(R.id.text_view_priority)
            imgMovie = itemView.findViewById(R.id.imgMovie)
            relRootProduct = itemView.findViewById(R.id.relRootProduct)
        }
    }
}