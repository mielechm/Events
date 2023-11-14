package com.mielechm.events.data.model

data class EventListEntry(
    val date: String,
    val id: String,
    val imageUrl: String,
    val subtitle: String,
    val title: String,
    val videoUrl: String?
)