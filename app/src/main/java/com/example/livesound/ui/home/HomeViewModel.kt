package com.example.livesound.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Artistas Nuevos"
    }
    val text: LiveData<String> = _text

    private val _textList = MutableLiveData<String>().apply {
        value = "Listas de Reproducción"
    }
    val textList: LiveData<String> = _textList
}