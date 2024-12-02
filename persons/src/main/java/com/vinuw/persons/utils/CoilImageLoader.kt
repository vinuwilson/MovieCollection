package com.vinuw.persons.utils

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vinuw.persons.R

@Composable
fun CoilImageImageLoader(
    modifier: Modifier = Modifier,
    context: Context,
    imageUri: String?,
    placeholder: Int = R.drawable.app_movie_image
) {
    if (imageUri.isNullOrEmpty()) {
        Image(
            painter = painterResource(id = R.drawable.app_movie_image),
            contentDescription = "Product image",
            contentScale = ContentScale.Inside,
            modifier = modifier
        )

    } else {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imageUri)
                .crossfade(true)
                .placeholder(placeholder)
                .build(),
            contentDescription = "Product image",
            error = painterResource(id = R.drawable.app_movie_image),
            contentScale = ContentScale.FillBounds,
            modifier = modifier
        )
    }
}