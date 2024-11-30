package com.vinuw.moviecollection.presenter.movie_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vinuw.moviecollection.R
import com.vinuw.moviecollection.domain.model.ResultModel
import com.vinuw.moviecollection.ui.theme.app_padding
import com.vinuw.moviecollection.ui.theme.card_elevation
import com.vinuw.moviecollection.ui.theme.card_padding
import com.vinuw.moviecollection.ui.theme.font_size_default
import com.vinuw.moviecollection.ui.theme.font_size_large
import com.vinuw.moviecollection.ui.theme.font_size_medium
import com.vinuw.moviecollection.ui.theme.spacer_height
import com.vinuw.moviecollection.ui.theme.text_medium_padding
import com.vinuw.moviecollection.utils.CoilImage
import com.vinuw.moviecollection.utils.Constants.BASE_IMAGE_URL

@Composable
fun DetailsInfoView(
    movieDetails: ResultModel
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .height((configuration.screenHeightDp / 1.5).dp)
                .fillMaxWidth()
                .padding(card_padding),
            elevation = CardDefaults.elevatedCardElevation(card_elevation),

            ) {
            CoilImage(
                context = context,
                imageUri = BASE_IMAGE_URL.plus(movieDetails.posterPath),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Text(
            text = movieDetails.title,
            modifier = Modifier.padding(text_medium_padding),
            fontSize = font_size_large,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.ExtraBold,
        )

        Spacer(modifier = Modifier.height(spacer_height))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = app_padding)
        ) {
            Icon(
                Icons.Sharp.CalendarMonth,
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = stringResource(id = R.string.calender_icon)
            )

            Text(
                text = movieDetails.releaseDate,
                fontSize = font_size_default,
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = FontFamily.Serif,
            )
        }

        Text(
            modifier = Modifier.padding(text_medium_padding),
            text = movieDetails.overview,
            fontSize = font_size_medium,
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = FontFamily.Serif,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsInfoViewPreview() {
    DetailsInfoView(
        movieDetails = ResultModel(
            backdropPath = "/3V4kLQg0kSqPLctI5ziYWabAZYF.jpg",
            id = 912649,
            originalLanguage = "en",
            originalTitle = "Venom: The Last Dance",
            overview = "Eddie and Venom are on the run. Hunted by both of their worlds and with the net closing in, the duo are forced into a devastating decision that will bring the curtains down on Venom and Eddie's last dance.",
            posterPath = "/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
            releaseDate = "2024-10-22",
            title = "Venom: The Last Dance"
        )
    )
}