package com.example.livesound.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaylistViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Favoritos Weekend"
    }
    val text: LiveData<String> = _text
}