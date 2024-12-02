package com.vinuw.persons.presenter.persons_info

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.vinuw.persons.R
import com.vinuw.persons.presenter.persons_info.components.PersonDetails
import com.vinuw.persons.ui.theme.title_size

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonInfo(
    personsState: PersonsState
) {

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.screen_title),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = title_size
                    )
                }
            )
        }
    ) { innerPadding ->
        if (personsState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
            }
        } else {
            if (personsState.personsList != null) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    items(personsState.personsList) { person ->
                        PersonDetails(
                            personDetails = person
                        )
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
