package com.mielechm.events.ui.views.eventslist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mielechm.events.data.model.EventListEntry
import com.mielechm.events.repository.EventsRepositoryImpl
import com.mielechm.events.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsListViewModel @Inject constructor(private val repositoryImpl: EventsRepositoryImpl) :
    ViewModel() {

    var eventsList = mutableStateOf<List<EventListEntry>>(listOf())
    var isLoading = mutableStateOf(false)
    var loadError = mutableStateOf("")

    init {
        getEvents()
    }

    fun getEvents() {

        viewModelScope.launch {
            isLoading.value = true

            when (val result = repositoryImpl.getEvents()) {
                is Resource.Success -> {
                    val eventsEntries = result.data!!.map { event ->
                        EventListEntry(
                            id = event.id,
                            date = event.date,
                            title = event.title,
                            subtitle = event.subtitle,
                            imageUrl = event.imageUrl,
                            videoUrl = event.videoUrl ?: ""
                        )
                    }
                    loadError.value = ""
                    isLoading.value = false
                    eventsList.value += eventsEntries
                }
                is Resource.Error -> {
                    loadError.value = result.message.toString()
                    isLoading.value = false
                }

                else -> {}
            }
        }
    }
}