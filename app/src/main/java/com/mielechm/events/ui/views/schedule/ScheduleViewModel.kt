package com.mielechm.events.ui.views.schedule

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mielechm.events.data.model.EventListEntry
import com.mielechm.events.repository.ScheduleRepositoryImpl
import com.mielechm.events.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val repositoryImpl: ScheduleRepositoryImpl) :
    ViewModel() {

    var schedule = mutableStateOf<List<EventListEntry>>(listOf())
    var isLoading = mutableStateOf(false)
    var loadError = mutableStateOf("")

    init {
        getSchedule()
    }

    fun getSchedule() {

        viewModelScope.launch {
            isLoading.value = true

            when (val result = repositoryImpl.getSchedule()) {
                is Resource.Success -> {
                    val scheduleEntries = result.data!!.map { event ->
                        EventListEntry(
                            id = event.id,
                            date = event.date,
                            title = event.title,
                            subtitle = event.subtitle,
                            imageUrl = event.imageUrl,
                            videoUrl = event.videoUrl
                        )
                    }
                    loadError.value = ""
                    isLoading.value = false
                    schedule.value += scheduleEntries
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