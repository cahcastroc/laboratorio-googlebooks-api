package br.edu.infnet.laboratorio_api_books.service


import br.edu.infnet.laboratorio_api_books.domain.QueryResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface BookApi {

    @GET("volumes")
    fun queryBooks(@Query("q") query: String?) : Call<QueryResult?>?
}