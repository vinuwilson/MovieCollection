package com.vinuw.moviecollection.presenter.movie_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vinuw.moviecollection.presenter.movie_details.components.DetailsInfoView
import com.vinuw.moviecollection.presenter.movie_list.MovieListState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetails(
    movieState: MovieListState,
    movieId: Int,
    onBottomSheetDismissed : () -> Unit
) {

    val movieDetails = movieState.movieList?.find { it.id == movieId }
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = { onBottomSheetDismissed()}
    ) {
        if (movieDetails != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
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