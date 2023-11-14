package com.mielechm.events.repository

import com.mielechm.events.data.remote.response.Event
import com.mielechm.events.utils.Resource


interface ScheduleRepository {
    suspend fun getSchedule(): Resource<List<Event>>

}
