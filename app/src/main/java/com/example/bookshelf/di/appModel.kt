package com.example.bookshelf.di

import com.example.bookshelf.data.BookShelfRepository
import com.example.bookshelf.data.NetworkBookShelfRepository
import com.example.bookshelf.network.BooksShelfApiService
import com.example.bookshelf.viewModel.BookViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModel = module {

    single {

        Json {

            ignoreUnknownKeys = true
            isLenient = true

        }

    }

    single {

        Retrofit.Builder()
            .addConverterFactory( get<Json>().asConverterFactory( "application/json".toMediaType() ) )
            .baseUrl( BooksShelfApiService.BASE_URL )
            .build()
            .create( BooksShelfApiService::class.java )

    }

    single < BookShelfRepository > {

        NetworkBookShelfRepository( get() )

    }

    viewModel {

        BookViewModel( get() )

    }

}