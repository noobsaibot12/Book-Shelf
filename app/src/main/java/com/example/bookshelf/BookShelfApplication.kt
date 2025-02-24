package com.example.bookshelf

import android.app.Application
import com.example.bookshelf.di.appModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BookShelfApplication: Application() {

//    lateinit var container: AppContainer
//    override fun onCreate() {
//
//        super.onCreate()
//        container = DefaultAppContainer()
//
//    }

    override fun onCreate() {

        super.onCreate()

        startKoin {

            androidContext( this@BookShelfApplication )
            modules(

                appModel

            )

        }

    }

}