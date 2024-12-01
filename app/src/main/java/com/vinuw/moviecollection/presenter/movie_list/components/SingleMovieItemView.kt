package com.vinuw.moviecollection.presenter.movie_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.vinuw.moviecollection.domain.model.ResultModel
import com.vinuw.moviecollection.ui.theme.card_elevation
import com.vinuw.moviecollection.ui.theme.card_height
import com.vinuw.moviecollection.ui.theme.card_padding
import com.vinuw.moviecollection.ui.theme.font_size_default
import com.vinuw.moviecollection.ui.theme.font_size_medium
import com.vinuw.moviecollection.ui.theme.image_height
import com.vinuw.moviecollection.ui.theme.text_padding
import com.vinuw.moviecollection.utils.CoilImage
import com.vinuw.moviecollection.utils.Constants.BASE_IMAGE_URL

@Composable
fun SingleMovieItemView(
    movieList: ResultModel,
    onItemClicked: (Int) -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .height( card_height)
            .padding(card_padding)
            .clickable { onItemClicked(movieList.id) },
        elevation = CardDefaults.elevatedCardElevation(card_elevation),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.onPrimary)
        ) {

            CoilImage(
                context = context,
                imageUri = BASE_IMAGE_URL.plus(movieList.posterPath),
                modifier = Modifier.height(image_height)
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = text_padding),
                textAlign = TextAlign.End,
                text = movieList.releaseDate,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic,
                fontSize = font_size_default,
                color = MaterialTheme.colorScheme.onSurface
            )

            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(text_padding),
                    text = movieList.title,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = font_size_medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleMovieItemViewPreview() {
    SingleMovieItemView(
        movieList = ResultModel(
            backdropPath = "/3V4kLQg0kSqPLctI5ziYWabAZYF.jpg",
            id = 912649,
            originalLanguage = "en",
            originalTitle = "Venom: The Last Dance",
            overview = "Eddie and Venom are on the run. Hunted by both of their worlds and with the net closing in, the duo are forced into a devastating decision that will bring the curtains down on Venom and Eddie's last dance.",
            posterPath = "/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
            releaseDate = "2024-10-22",
            title = "Venom: The Last Dance"
        ),
        onItemClicked = {}
    )
}