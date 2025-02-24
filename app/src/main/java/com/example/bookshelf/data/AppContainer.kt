package com.example.bookshelf.data

//import com.example.bookshelf.network.BooksShelfApiService
//import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
//import kotlinx.serialization.json.Json
//import okhttp3.MediaType.Companion.toMediaType
//import retrofit2.Retrofit
//
//interface AppContainer{
//
//    val bookShelfRepository: BookShelfRepository
//
//}
//
//class DefaultAppContainer: AppContainer {
//
//    private val baseUrl = "https://www.googleapis.com/books/v1/"
//
//    private val json = Json {
//        ignoreUnknownKeys = true // Ignores fields in the JSON that aren't in your model
//        isLenient = true         // Allows lenient parsing of JSON
//    }
//
//    private val retrofit: Retrofit = Retrofit.Builder()
//        .addConverterFactory( json.asConverterFactory( "application/json".toMediaType() ) )
//        .baseUrl( baseUrl )
//        .build()
//
//    private val retrofitService: BooksShelfApiService by lazy {
//
//        retrofit.create( BooksShelfApiService::class.java )
//
//    }
//
//    override val bookShelfRepository: BookShelfRepository by lazy {
//
//        NetworkBookShelfRepository( retrofitService )
//
//    }
//
//}