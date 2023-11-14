package com.mielechm.events.repository

import com.mielechm.events.data.remote.ScheduleApi
import com.mielechm.events.data.remote.response.Event
import com.mielechm.events.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ScheduleRepositoryImpl @Inject constructor(
    private val api: ScheduleApi
) : ScheduleRepository {

    override suspend fun getSchedule(): Resource<List<Event>> {
        val response = try {
            api.getSchedule()
        } catch (e: Exception) {
            return Resource.Error("Error occurred: ${e.message}")
        }
        return Resource.Success(response)
    }

}