package com.example.livesound.ui.player

import android.app.Application
import android.widget.SeekBar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class PlayerViewModel(application: Application): AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is player Fragment"
    }
    val text: LiveData<String> = _text

    private val exoPlayer: androidx.media3.exoplayer.ExoPlayer by lazy {
        androidx.media3.exoplayer.ExoPlayer.Builder(application).build()
    }

    fun getPlayer(): androidx.media3.exoplayer.ExoPlayer {
        return exoPlayer
    }

    fun prepareAndPlay(uri: String) {
        val mediaItem = androidx.media3.common.MediaItem.fromUri(uri)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()
    }
}