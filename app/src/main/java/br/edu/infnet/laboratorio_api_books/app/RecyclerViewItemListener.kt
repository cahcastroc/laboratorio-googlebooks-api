package br.edu.infnet.laboratorio_api_books.app

import android.view.View

interface RecyclerViewItemListener {

    fun itemClicked(it: View?, id: String)

    fun buttonClicked(view: View, id : String)
}