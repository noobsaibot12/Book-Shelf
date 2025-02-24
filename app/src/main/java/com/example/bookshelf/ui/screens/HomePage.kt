package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.ui.utils.BookCard
import com.example.bookshelf.ui.utils.ErrorScreen
import com.example.bookshelf.ui.utils.LoadingScreen
import com.example.bookshelf.viewModel.ListOfBooks

@Composable
fun HomePage (

    modifier: Modifier,
    listOfBooks: ListOfBooks,
    onClick: (BookItem) -> Unit

) {

//    Text ( text = title , modifier = modifier )

    when(  listOfBooks ) {

        is ListOfBooks.Loading -> {

            LoadingScreen( modifier = modifier )

        }
        is ListOfBooks.Success -> {

            BooksGrid(

                modifier = modifier,
                listOfBooks = listOfBooks.books.items,
                onClick = onClick

            )

        }
        is ListOfBooks.Error -> {

            ErrorScreen( modifier = modifier )

        }


    }

}


@Composable
fun BooksGrid(

    modifier: Modifier,
    listOfBooks: List<BookItem>,
    onClick: (BookItem) -> Unit

){

    Box(

        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart

    ) {

        LazyVerticalGrid (

            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy( 10.dp ),
            horizontalArrangement = Arrangement.spacedBy( 10.dp )

        ) {

            items( listOfBooks ) { book ->

                BookCard( book = book , onClick = onClick )

//                Text ( text = book.volumeInfo.title)

            }

        }

    }

}

