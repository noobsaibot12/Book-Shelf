package com.example.bookshelf.data

import com.example.bookshelf.model.BooksResponse
import com.example.bookshelf.network.BooksShelfApiService
import retrofit2.http.Query

interface BookShelfRepository {

    suspend fun getBooks(

        @Query("q") query: String,
        @Query("key") apiKey: String

    ): BooksResponse

}

class NetworkBookShelfRepository (

    private val booksShelfApiService: BooksShelfApiService

): BookShelfRepository {

    override suspend fun getBooks(

        @Query("q") query: String,
        @Query("key") apiKey: String

    ): BooksResponse = booksShelfApiService.getBooks( query = query , apiKey = apiKey )

}