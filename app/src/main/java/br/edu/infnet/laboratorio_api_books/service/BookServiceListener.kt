package br.edu.infnet.laboratorio_api_books.service

import br.edu.infnet.laboratorio_api_books.domain.QueryResult

interface BookServiceListener {

    fun onResponse(queryResult: QueryResult?)

    fun onFailure(message: String?)
}
