package com.mielechm.events.data.remote.response

data class Event(
    val date: String,
    val id: String,
    val imageUrl: String,
    val subtitle: String,
    val title: String,
    val videoUrl: String? = null
)