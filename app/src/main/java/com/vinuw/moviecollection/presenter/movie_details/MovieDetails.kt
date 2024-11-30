package com.vinuw.moviecollection.presenter.movie_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.vinuw.moviecollection.R
import com.vinuw.moviecollection.presenter.movie_details.components.DetailsInfoView
import com.vinuw.moviecollection.presenter.movie_list.MovieListState
import com.vinuw.moviecollection.ui.theme.title_size

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetails(
    movieState: MovieListState,
    movieId: Int
) {

    val movieDetails = movieState.movieList?.find { it.id == movieId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_details_title),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = title_size
                    )
                }
            )
        }
    ) { innerPadding ->
        if (movieDetails != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colorScheme.onPrimary),
            ) {
                DetailsInfoView(
                    movieDetails = movieDetails
                )
            }
        }
    }
}