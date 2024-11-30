package com.vinuw.moviecollection.presenter.movie_list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.vinuw.moviecollection.R
import com.vinuw.moviecollection.presenter.movie_list.components.SingleMovieItemView
import com.vinuw.moviecollection.ui.theme.title_size

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieList(
    movieState: MovieListState,
    onItemClicked: (Int) -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_title),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = title_size
                    )
                }
            )
        }
    ) { innerPadding ->
        if (movieState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
            }
        } else {
            if (movieState.movieList != null) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    columns = GridCells.Fixed(2)
                ) {
                    items(movieState.movieList) { movieList ->
                        SingleMovieItemView(movieList) { movieID ->
                            onItemClicked(movieID)
                        }
                    }
                }
            } else {
                LaunchedEffect(Unit) {
                    Toast.makeText(
                        context,
                        "No internet connection...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}