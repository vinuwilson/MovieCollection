package com.vinuw.persons.presenter.persons_info.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.vinuw.persons.domain.model.PersonsModel
import com.vinuw.persons.ui.theme.app_padding
import com.vinuw.persons.ui.theme.card_elevation
import com.vinuw.persons.ui.theme.card_padding
import com.vinuw.persons.ui.theme.font_size_default
import com.vinuw.persons.ui.theme.font_size_large
import com.vinuw.persons.ui.theme.font_size_medium
import com.vinuw.persons.ui.theme.image_size
import com.vinuw.persons.utils.CoilImageImageLoader
import com.vinuw.persons.utils.Constants.BASE_IMAGE_URL

@Composable
fun PersonDetails(
    personDetails: PersonsModel
) {

    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(card_padding),
        elevation = CardDefaults.elevatedCardElevation(card_elevation),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            CoilImageImageLoader(
                context = context,
                imageUri = BASE_IMAGE_URL.plus(personDetails.profilePath),
                modifier = Modifier
                    .size(image_size)
                    .clip(RectangleShape)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(app_padding)
            ) {
                Text(
                    text = personDetails.name,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = font_size_large,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = personDetails.originalName,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = font_size_medium,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = personDetails.knownForDepartment,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = font_size_default,
                    fontWeight = FontWeight.Thin
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonsDetailsPreview() {
    PersonDetails(
        personDetails = PersonsModel(
            adult = false,
            gender = 1,
            id = 2994385,
            knownForDepartment = "Acting",
            name = "Thea D'souza",
            originalName = "Thea D'souza",
            popularity = 233.419,
            profilePath = "/c6jUosKIndiFtnsw5wY5KgtHjx7.jpg"
        )
    )
}