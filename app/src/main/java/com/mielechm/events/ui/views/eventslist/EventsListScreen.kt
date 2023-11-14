package com.mielechm.events.ui.views.eventslist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mielechm.events.data.model.EventListEntry

@Composable
fun EventsListScreen(
    navController: NavController,
    viewModel: EventsListViewModel = hiltViewModel()
) {

    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            EventsList(navController = navController)
        }
    }

}

@Composable
fun EventsList(navController: NavController, viewModel: EventsListViewModel = hiltViewModel()) {

    val events by remember { viewModel.eventsList }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {

        itemsIndexed(events, key = { index, event -> event.id }) { index, event ->
            EventEntry(event = event, navController = navController)
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
        if (loadError.isNotEmpty()) {
            Text(
                text = loadError,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Composable
fun EventEntry(event: EventListEntry, navController: NavController) {
    Column(modifier = Modifier.fillMaxHeight()) {
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                AsyncImage(model = event.imageUrl, contentDescription = null)
            }
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .padding(start = 8.dp)
            ) {

                Text(text = event.title, maxLines = 1, overflow = TextOverflow.Ellipsis)

                Text(text = event.subtitle, maxLines = 1, overflow = TextOverflow.Ellipsis)

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = event.date, maxLines = 1, overflow = TextOverflow.Ellipsis)

            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
    }
}
