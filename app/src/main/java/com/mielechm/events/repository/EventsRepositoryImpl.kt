package com.mielechm.events.repository

import com.mielechm.events.data.remote.EventsApi
import com.mielechm.events.data.remote.response.Event
import com.mielechm.events.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class EventsRepositoryImpl @Inject constructor(private val api: EventsApi) : EventsRepository {

    override suspend fun getEvents(): Resource<List<Event>> {
        val response = try {
            api.getEvents()
        } catch (e: Exception) {
            return Resource.Error("Error occurred: ${e.message}")
        }
        return Resource.Success(response)
    }
}