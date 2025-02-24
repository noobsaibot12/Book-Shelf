package com.example.bookshelf.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.constants.API_KEY
import com.example.bookshelf.ui.screens.DetailScreen
import com.example.bookshelf.ui.screens.HomePage
import com.example.bookshelf.ui.screens.LandingPage
import com.example.bookshelf.viewModel.BookViewModel
import org.koin.androidx.compose.koinViewModel

enum class Screens ( val title: String ) {

    LANDING_PAGE ( title = "Book Shelf" ),
    HOME_SCREEN ( title = "List Of Books" ),
    DETAILS_SCREEN ( title = "Details Screen" )

}

@Composable
fun BookShelfApp () {

    val navController = rememberNavController()
//    val booksViewModel: BookViewModel = viewModel( factory = BookViewModel.Factory )
    val booksViewModel: BookViewModel = koinViewModel()
    val booksUiState by booksViewModel.uiState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screens.valueOf(

        backStackEntry?.destination?.route ?: Screens.LANDING_PAGE.name

    )

    Scaffold (

        topBar = {

            BookShelfAppBar(

                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navController = navController

            )

        }

    ) { innerPadding ->

        NavHost(

            navController = navController,
            startDestination = Screens.LANDING_PAGE.name

        ) {

            composable(

                route = Screens.LANDING_PAGE.name

            ) {

                var qry by rememberSaveable { mutableStateOf("") }

                LandingPage (

                    modifier = Modifier.padding( innerPadding ),
                    onClick = { title ->

                        booksViewModel.updateQuery( query = title )
                        booksViewModel.searchBooks( query = title , apiKey = API_KEY )
                        navController.navigate( Screens.HOME_SCREEN.name )

                    },
                    query = qry,
                    onValueChange = {

                        qry = it

                    }

                )

            }

            composable(

                route = Screens.HOME_SCREEN.name

            ) {

                HomePage(

                    listOfBooks = booksViewModel.listOfBooks,
                    modifier = Modifier.padding( innerPadding ),
                    onClick = { currentBook ->

                        booksViewModel.updateCurrentBook( book = currentBook )
                        navController.navigate(

                            route = Screens.DETAILS_SCREEN.name

                        )

                    }

                )

            }

            composable(

                route = Screens.DETAILS_SCREEN.name

            ) {

                val context = LocalContext.current

                DetailScreen(

                    modifier = Modifier.padding( innerPadding ),
                    book = booksUiState.currentBook!!,
                    onBuy = {

                        booksViewModel.searchOnWeb(

                            context = context,
                            link = booksUiState.currentBook!!.saleInfo?.buyLink

                        )

                    },
                    onWebRead = {

                        booksViewModel.searchOnWeb(

                            context = context,
                            link = booksUiState.currentBook!!.accessInfo?.webReaderLink

                        )

                    }

                )

            }

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfAppBar(

    currentScreen: Screens,
    canNavigateBack: Boolean,
    navController: NavHostController

) {

    CenterAlignedTopAppBar(

        title = {

            Text( text = currentScreen.title )

        },
        navigationIcon = {

            if ( canNavigateBack ) {

                Icon(

                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {

                            navController.popBackStack()

                        }

                )

            }

        }

    )

}
