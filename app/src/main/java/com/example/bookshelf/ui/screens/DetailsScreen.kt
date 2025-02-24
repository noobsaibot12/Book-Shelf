package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.BookItem

//@Preview( showBackground = true )
@Composable
fun DetailScreen (

    modifier: Modifier = Modifier,
    book: BookItem,
    onBuy: () -> Unit,
    onWebRead: () -> Unit

) {

//    Text ( text = book.volumeInfo.title, modifier = modifier )

    val url = book.volumeInfo.imageLinks?.thumbnail

    Column (

        modifier = modifier
            .fillMaxWidth()
            .padding( 20.dp )

    ) {

        Box(

            modifier = Modifier
                .fillMaxWidth()
                .size(230.dp)

        ) {

            if (url != null) {
                AsyncImage(

                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(

                            if ( url.startsWith("http://") ) {

                                url.replaceFirst("http://", "https://")

                            } else {

                                url

                            }

                        )
                        .crossfade(true)
                        .build(),
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth()

                )
            }
        }

        Column (

            modifier = Modifier.fillMaxWidth().verticalScroll( rememberScrollState() )

        ) {

            DetailScreenCard(

                book = book,
                onBuy = onBuy

            )


            Text(

                text = "DESCRIPTION OF BOOK:",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                maxLines = 2

            )

            Spacer(modifier = Modifier.height(5.dp))

            Text("${book.volumeInfo.description}")

            Spacer(Modifier.height(20.dp))

            Text(

                text = "TOTAL PAGES IN BOOK:",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,

            )

            Text(

                "This book contains ${book.volumeInfo.pageCount} pages",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(vertical = 10.dp)

            )

            Spacer(Modifier.height(2.dp))

            Button(

                onClick = {

                    onWebRead()

                },
                modifier = Modifier.fillMaxWidth()

            ) {

                Text(text = "Read Online!!")

            }

        }

    }

}


//@Preview( showBackground = true )
@Composable
fun DetailScreenCard(

    modifier: Modifier = Modifier,
    book: BookItem,
    onBuy: () -> Unit

) {

    val authors = book.volumeInfo.authors.orEmpty() // Handle null authors safely

    Card(

        modifier = modifier
            .fillMaxWidth()
            .size(190.dp)
            .padding(vertical = 10.dp),
        elevation = CardDefaults.cardElevation(4.dp)

    ) {

        // Text Overlay
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,

        ) {
            // Title
            Text(

                text = "TITLE: ${book.volumeInfo.title}",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis

            )

            Spacer(modifier = Modifier.height(10.dp))

            // Authors
            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Text(

                    text = "Authors: ",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White.copy(alpha = 0.9f),

                )

                Text(

                    text = authors.joinToString(", "), // Safely display all authors
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            // Publisher
            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Text(

                    text = "Publisher: ",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White.copy(alpha = 0.9f),

                )

                Text(

                    text = book.volumeInfo.publisher.orEmpty(),
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )

            }

            Spacer(modifier = Modifier.height(15.dp))

            // Buy Button
            Box(

                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center

            ) {

                Button(onClick = { onBuy() }) {

                    Text(text = "Buy")

                }

            }

        }

    }

}