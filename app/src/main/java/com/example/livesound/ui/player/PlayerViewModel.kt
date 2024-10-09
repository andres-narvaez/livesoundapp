package com.example.livesound.ui.player
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class PlayerViewModel(application: Application): AndroidViewModel(application) {
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