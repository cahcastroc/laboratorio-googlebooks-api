package br.edu.infnet.laboratorio_api_books.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.laboratorio_api_books.R
import br.edu.infnet.laboratorio_api_books.app.BookAdpater
import br.edu.infnet.laboratorio_api_books.app.RecyclerViewItemListener
import br.edu.infnet.laboratorio_api_books.domain.QueryItem
import br.edu.infnet.laboratorio_api_books.domain.QueryResult
import br.edu.infnet.laboratorio_api_books.service.BookService
import br.edu.infnet.laboratorio_api_books.service.BookServiceListener
import java.lang.NullPointerException

class MainActivity : AppCompatActivity(), RecyclerViewItemListener,BookServiceListener {

    private val bookService = BookService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //----------Spinner

        val options = arrayOf("Titulo", "Autor","ISBN")
        val spOptions = findViewById<Spinner>(R.id.spOptions)
        spOptions.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        spOptions.setSelection(0)

        //----------------Configurar o Recycler View

        val rvResult = findViewById<RecyclerView>(R.id.rvResult)
        rvResult.layoutManager = GridLayoutManager(this,3)
        val adapter = BookAdpater(this)
        rvResult.adapter = adapter

        //---------------------------Service da Api

        bookService.setBookServiceListener(this)
        val btQuery = findViewById<Button>(R.id.btQuery)

        btQuery.setOnClickListener {

//
            var option = spOptions.selectedItem.toString()
            val etQuery = findViewById<EditText>(R.id.etQuery)
            if (etQuery.text.toString().isNotEmpty()) {
                Log.i("DR3", "Clicou no botão btQuery, não está vazio option ${option} e etquery ${etQuery.text.toString()}")
                bookService.queryBooks(option, etQuery.text.toString())

            }
        }


    }

    override fun itemClicked(it: View?, id: String) {

    }

    override fun buttonClicked(view: View, id: String) {

    }

    override fun onResponse(queryResult: QueryResult?) {

        try {
            if (queryResult != null) {
                val lstQueryItens = queryResult.items
                val bookList = ArrayList<QueryItem>()
                Log.i("DR3", "Resultado com ${lstQueryItens.size} itens")

                for (queryItem in lstQueryItens) {
                    bookList.add(queryItem)

                }

                // Recycler view com o Resultado
                val rvResult = this.findViewById<RecyclerView>(R.id.rvResult)
                val adapter = BookAdpater(this)
                adapter.bookList = bookList
                rvResult.adapter = adapter
            }
        } catch (ex: NullPointerException){
            Toast.makeText(this,"Erro! Não localizado",Toast.LENGTH_LONG).show()
        }
    }

    override fun onFailure(message: String?) {

    }


}