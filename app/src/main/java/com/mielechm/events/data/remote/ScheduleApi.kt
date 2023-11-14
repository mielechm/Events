package com.mielechm.events.data.remote

import com.mielechm.events.data.remote.response.Event
import retrofit2.http.GET

interface ScheduleApi {

    @GET("/getSchedule")
    suspend fun getSchedule(): List<Event>

}