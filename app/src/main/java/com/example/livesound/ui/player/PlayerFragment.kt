package com.example.livesound.ui.player

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.livesound.R
import com.example.livesound.databinding.FragmentPlayerBinding
import kotlin.math.min
import kotlin.math.max

class PlayerFragment : Fragment() {

    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!

    private lateinit var mediaPlayer: MediaPlayer
    private var isPlaying = false
    private var isSeekBarUpdating = false // Variable para controlar si la seekBar está actualizándose

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)

        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inicializar el MediaPlayer con una canción
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.song) // Reemplazar con tu canción

        // Mostrar el título de la canción
        binding.songTitle.text = "Bad Guy"
        binding.artistName.text = "Billie Eilish"

        // Botón de Play/Pausa
        binding.buttonPlayPause.setOnClickListener {
            if (isPlaying) {
                mediaPlayer.pause()
                binding.buttonPlayPause.setImageResource(R.drawable.play)
            } else {
                mediaPlayer.start()
                binding.buttonPlayPause.setImageResource(R.drawable.pause)
            }
            isPlaying = !isPlaying
        }

        // Controlar la barra de progreso
        mediaPlayer.setOnPreparedListener {
            binding.seekBar.max = it.duration
            isSeekBarUpdating = true // Inicia la actualización de la SeekBar
            binding.seekBar.postDelayed(updateSeekBar, 1000)
        }

        // Agregar funcionalidad para avanzar y retroceder
        binding.buttonForward.setOnClickListener { fastForward() }
        binding.buttonRewind.setOnClickListener { rewind() }

        // Listener para la SeekBar
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        val webView = binding.webView
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        // Inicialmente el WebView está oculto
        binding.webviewContainer.visibility = View.GONE

        // Botón de Cerrar WebView
        binding.btnCloseWebview.setOnClickListener {
            binding.webviewContainer.visibility = View.GONE // Oculta el WebView
        }

        // Agregar eventos a los botones
        binding.btnAppleMusic.setOnClickListener {
            openWebPage(webView, "https://music.apple.com/es/artist/billie-eilish/1065981054")
        }

        binding.btnSpotify.setOnClickListener {
            openWebPage(webView, "https://open.spotify.com/artist/6qqNVTkY8uBg9cP3Jd7DAH")
        }

        binding.btnPrimeMusic.setOnClickListener {
            openWebPage(webView, "https://music.amazon.es/artists/B01A7UTWX8/billie-eilish")
        }

        binding.btnDeezer.setOnClickListener {
            openWebPage(webView, "https://www.deezer.com/mx/artist/9635624")
        }

        binding.btnSoundcloud.setOnClickListener {
            openWebPage(webView, "https://soundcloud.com/billieeilish")
        }

        return root
    }

    private fun openWebPage(webView: WebView, url: String) {
        binding.webviewContainer.visibility = View.VISIBLE // Muestra el WebView
        webView.loadUrl(url)
    }

    // Función para avanzar 10 segundos
    private fun fastForward() {
        val newPosition = mediaPlayer.currentPosition + 10000 // Avanzar 10 segundos
        mediaPlayer.seekTo(min(newPosition, mediaPlayer.duration))
    }

    // Función para retroceder 10 segundos
    private fun rewind() {
        val newPosition = mediaPlayer.currentPosition - 10000 // Retroceder 10 segundos
        mediaPlayer.seekTo(max(newPosition, 0))
    }

    private val updateSeekBar = object : Runnable {
        override fun run() {
            if (isSeekBarUpdating) {
                binding.seekBar.progress = mediaPlayer.currentPosition
                binding.seekBar.postDelayed(this, 1000)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.release()
        _binding = null
        isSeekBarUpdating = false // Detener la actualización de la SeekBar
    }
}
