package app.com.retrofitwithrecyclerviewkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.api.apifromscratch.R
import cat.api.apifromscratch.api.models.AuthorData

class RecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    var authorList: List<AuthorData> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.author_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return authorList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.authorName.text = authorList[position].nomAutor
        holder.authorId.text = authorList[position].idAutor.toString()
    }

    fun setMovieListItems(movieList: List<AuthorData>) {
        this.authorList = movieList;
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val authorName: TextView = itemView!!.findViewById(R.id.nom_autor)
        val authorId: TextView = itemView!!.findViewById(R.id.id_autor)

    }
}