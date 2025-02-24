package com.example.bookshelf.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.BookItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCarousel(

    urls: List<Pair<String, String>>,
    onClick: (String) -> Unit

) {

    val carouselState = rememberCarouselState { 7 }

    Box(

        modifier = Modifier
            .fillMaxWidth()

    ) {

        HorizontalMultiBrowseCarousel(

            state = carouselState,
            preferredItemWidth = 200.dp,
            itemSpacing = 10.dp,

            ) { page ->

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {

                AsyncImage(

                    model = ImageRequest.Builder(context = LocalContext.current).data(

                        when (page) {

                            0 -> urls[0].first
                            1 -> urls[1].first
                            2 -> urls[2].first
                            3 -> urls[3].first
                            4 -> urls[4].first
                            5 -> urls[5].first
                            else -> urls[6].first

                        }

                    )
                        .crossfade(true).build(),
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {

                            onClick(

                                when (page) {

                                    0 -> urls[0].second
                                    1 -> urls[1].second
                                    2 -> urls[2].second
                                    3 -> urls[3].second
                                    4 -> urls[4].second
                                    5 -> urls[5].second
                                    else -> urls[6].second

                                }

                            )

                        }

                )

                Box(

                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(

                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))

                            )

                        )

                )

                Text(

                    text = when (page) {

                        0 -> urls[0].second
                        1 -> urls[1].second
                        2 -> urls[2].second
                        3 -> urls[3].second
                        4 -> urls[4].second
                        5 -> urls[5].second
                        else -> urls[6].second

                    },
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(8.dp)

                )

            }

        }

    }

}

@Composable
fun CustomTextField (

    value: String = "",
    onValueChange: (String) -> Unit = {},
    label: String = "",
    singleLine: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: @Composable () -> Unit = {}

) {

    OutlinedTextField(

        value = value,
        onValueChange = onValueChange,
        label = { Text( text = label ) },
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(

            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done

        ),
        keyboardActions = keyboardActions,
        singleLine = singleLine

    )

}

//SCREENS


//@Preview(showBackground = true)
@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    book: BookItem,
    onClick: (BookItem) -> Unit
) {
    val url = book.volumeInfo.imageLinks?.thumbnail

    Card(

        modifier = modifier
            .fillMaxWidth()
            .size(250.dp)
            .clickable { onClick(book) },
        elevation = CardDefaults.cardElevation(4.dp)

    ) {

        Box {

            // Image Section
            if (url != null) {

                AsyncImage(

                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            if (url.startsWith("http://")) {
                                url.replaceFirst("http://", "https://")
                            } else {
                                url
                            }
                        )
                        .crossfade(true)
                        .build(),
                    contentDescription = "Book cover of ${book.volumeInfo.title}",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop // Adjust the scaling if needed

                )

            }


            Box(

                modifier = Modifier
                    .fillMaxSize()
                    .background(

                        brush = Brush.verticalGradient(

                            colors = listOf(

                                Color.Black.copy(alpha = 0.8f),
                                Color.Black.copy(alpha = 0.8f) // Bottom dark overlay
                            )

                        )

                    )

            )

            // Details Section
            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Bottom

            ) {

                // Book Title
                Text(

                    text = book.volumeInfo.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis

                )

                // Book Description
                Text(

                    text = book.volumeInfo.description.orEmpty(),
                    maxLines = 3,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.9f),
                    overflow = TextOverflow.Ellipsis

                )

                Spacer(modifier = Modifier.height(5.dp))

                // Additional Info
                Text(

                    text = book.volumeInfo.pageCount?.let { "Pages: $it" }.orEmpty(),
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1

                )

                Text(

                    text = book.volumeInfo.publisher?.let { "Publisher: $it" }.orEmpty(),
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1

                )

                Text(

                    text = book.saleInfo?.retailPrice?.amount?.let { "Price: $it rs" }.orEmpty(),
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1

                )

            }

        }

    }

}



@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {

    Column(

        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Image(

            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""

        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))

    }

}

@Composable
fun LoadingScreen (modifier: Modifier = Modifier) {

    Box (

        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {

        Image(

            painter = painterResource(id = R.drawable.loading_img),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()

        )

    }

}