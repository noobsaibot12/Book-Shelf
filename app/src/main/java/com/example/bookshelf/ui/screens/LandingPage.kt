package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookshelf.constants.firstImageUrls
import com.example.bookshelf.constants.musicCategories
import com.example.bookshelf.constants.romanticBooks
import com.example.bookshelf.constants.secondImageUrls
import com.example.bookshelf.ui.utils.CustomTextField
import com.example.bookshelf.ui.utils.MyCarousel

@Composable
fun LandingPage (

    modifier: Modifier,
    onClick: (String) -> Unit,
    query: String,
    onValueChange: (String) -> Unit

) {

    ResultScreen(

        modifier = modifier,
        onClick = onClick,
        onValueChange = onValueChange,
        query = query

    )

}

@Composable
fun ResultScreen(

    modifier: Modifier,
    onClick: (String) -> Unit,
    onValueChange: (String) -> Unit,
    query: String

) {

    Column (

        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        CustomTextField(

            label = "Enter the book name",
            value = query,
            onValueChange = onValueChange,
            keyboardActions = KeyboardActions(

                onDone = {

                    onClick(query)

                }

            )

        )

        Spacer ( modifier = Modifier.height( 50.dp ) )

        Column (

            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,

        ){

            Text(text = "Some Top Books Category" , style = MaterialTheme.typography.titleLarge )

            Spacer(modifier = Modifier.height(10.dp))

            MyCarousel(

                urls = firstImageUrls,
                onClick = onClick

            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "Some Top Random Books Category" , style = MaterialTheme.typography.titleLarge)

            Spacer ( modifier = Modifier.height( 10.dp ) )

            MyCarousel(

                urls = secondImageUrls,
                onClick = onClick

            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "Some Top Music Genre" , style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(10.dp))

            MyCarousel(

                urls = musicCategories,
                onClick = onClick,

            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "Some Top Romantic Books Category" , style = MaterialTheme.typography.titleLarge)

            Spacer ( modifier = Modifier.height( 10.dp ) )

            MyCarousel(

                urls = romanticBooks,
                onClick = onClick,

            )

        }

    }

}



