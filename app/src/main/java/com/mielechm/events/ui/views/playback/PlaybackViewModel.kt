package com.mielechm.events.ui.views.playback

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.media3.common.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaybackViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val player: Player,
) : ViewModel() {

    val videoUrl = savedStateHandle.getStateFlow("videoUrl", "")

    init {
        player.prepare()
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

}