package com.example.bookshelf.viewModel

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.BookShelfRepository
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.BooksResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface ListOfBooks {

    data class Success( val books: BooksResponse): ListOfBooks
    object Error: ListOfBooks
    object Loading: ListOfBooks

}

class BookViewModel(

    private val bookShelfRepository: BookShelfRepository

): ViewModel() {

    private val _uiState = MutableStateFlow(BooksUiState())
    val uiState = _uiState.asStateFlow()
    var listOfBooks: ListOfBooks by mutableStateOf( ListOfBooks.Loading )
    private set

    fun searchBooks(query: String , apiKey: String) {

        viewModelScope.launch {

            listOfBooks = ListOfBooks.Loading
            listOfBooks = try {

                ListOfBooks.Success( books = bookShelfRepository.getBooks( query = query , apiKey = apiKey ) )

            } catch (e: Exception) {

                ListOfBooks.Error

            }

        }

    }

    fun updateCurrentBook(book: BookItem) {

        _uiState.update {

            it.copy(

                currentBook = book

            )

        }

    }

    fun updateQuery(query: String) {

        _uiState.update {

            it.copy(currentQueryParam = query)

        }

    }

    fun searchOnWeb(context: Context, link: String? ) {

        if ( link == null ) {

            Toast.makeText(context, "No link found to search", Toast.LENGTH_SHORT).show()

        } else {

            val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {

                putExtra(SearchManager.QUERY, link)

            }

            context.startActivity( intent )

        }

    }

//    companion object {
//
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//
//            initializer {
//
//                val application = ( this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookShelfApplication )
//                val bookShelfRepository = application.container.bookShelfRepository
//                BookViewModel ( bookShelfRepository = bookShelfRepository )
//
//            }
//
//        }
//
//    }

}

data class BooksUiState (

    val currentBook: BookItem? = null,
    val currentQueryParam: String = ""

)