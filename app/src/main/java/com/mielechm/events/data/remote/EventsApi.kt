package com.mielechm.events.data.remote

import com.mielechm.events.data.remote.response.Event
import retrofit2.http.GET

interface EventsApi {

    @GET("/getEvents")
    suspend fun getEvents(): List<Event>
}