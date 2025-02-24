package com.example.bookshelf.network

import com.example.bookshelf.model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface BooksShelfApiService {

    @GET("volumes")
    suspend fun getBooks(

        @Query("q") query: String,
        @Query("key") apiKey: String

    ): BooksResponse

    companion object {

        const val BASE_URL = "https://www.googleapis.com/books/v1/"

    }

}