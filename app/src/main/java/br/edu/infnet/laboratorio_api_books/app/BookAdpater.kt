package br.edu.infnet.laboratorio_api_books.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.laboratorio_api_books.R
import br.edu.infnet.laboratorio_api_books.domain.QueryItem
import com.bumptech.glide.Glide

class BookAdpater(listener: RecyclerViewItemListener): RecyclerView.Adapter<BookAdpater.ViewHolder>() {

    private var listener = listener
    var bookList = listOf<QueryItem>()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }



    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItem(book: QueryItem, itemListener: RecyclerViewItemListener,position: Int){

            val imageView = itemView.findViewById<ImageView>(R.id.imageView)

            if(book.volumeInfo.imageLinks != null && book.volumeInfo.imageLinks!!.smallThumbnail != null){
                val url = book.volumeInfo.imageLinks!!.smallThumbnail.toString()
                Glide.with(itemView.context).load(url).into(imageView)
            }

            val tvTitulo = itemView.findViewById<TextView>(R.id.tvTitulo)
            tvTitulo.text = book.volumeInfo.title
            val tvAutores = itemView.findViewById<TextView>(R.id.tvAutores)
            tvAutores.text = book.volumeInfo.authors.toString()

//            val button = itemView.findViewById<Button>(R.id.btQuery)
//
//            button.setOnClickListener{
//                itemListener.buttonClicked(it,book.id!!)
//            }





        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.books_row, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(bookList[position], listener,position)
    }

    override fun getItemCount(): Int {
       return bookList.size
    }



}