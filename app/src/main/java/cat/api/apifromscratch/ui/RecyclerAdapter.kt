package cat.api.apifromscratch.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.api.apifromscratch.R
import cat.api.apifromscratch.api.models.AuthorData

class RecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    var authorList: List<AuthorData> = listOf()
    lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        //fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.author_item, parent, false)
        return MyViewHolder(view, mListener)
    }

    override fun getItemCount(): Int {
        return authorList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.authorName.text = authorList[position].nomAutor
        holder.authorId.text = authorList[position].idAutor.toString()
        holder.deleteBtn.setOnClickListener {
            mListener.onDeleteClick(position)
        }
    }

    fun setAuthorListItems(authorList: List<AuthorData>) {
        this.authorList = authorList;
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView!!) {

        val authorName: TextView = itemView!!.findViewById(R.id.nom_autor)
        val authorId: TextView = itemView!!.findViewById(R.id.id_autor)
        val deleteBtn: ImageView = itemView!!.findViewById(R.id.deleteBtn)

    }
}