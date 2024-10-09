package com.example.livesound.ui.player

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.livesound.R
import com.example.livesound.databinding.FragmentPlayerBinding

class PlayerFragment : Fragment() {

    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!

    private lateinit var mediaPlayer: MediaPlayer
    private var isPlaying = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)

        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inicializar el MediaPlayer con una canción
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.song) // Reemplazar con tu canción

        // Mostrar el título de la canción
        val textView: TextView = binding.songTitle
        textView.text = "Bad Guy"

        val textViewArtist: TextView = binding.artistName
        textViewArtist.text = "Billie Eilish"

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
        binding.seekBar.max = mediaPlayer.duration
        mediaPlayer.setOnBufferingUpdateListener { _, percent ->
            binding.seekBar.secondaryProgress = percent
        }

        mediaPlayer.setOnPreparedListener {
            binding.seekBar.max = it.duration
            binding.seekBar.postDelayed(updateSeekBar, 1000)
        }

        // Agregar funcionalidad para avanzar y retroceder
        binding.buttonForward.setOnClickListener {
            fastForward()
        }

        binding.buttonRewind.setOnClickListener {
            rewind()
        }

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

        return root
    }

    // Función para avanzar 10 segundos
    private fun fastForward() {
        val newPosition = mediaPlayer.currentPosition + 10000 // Avanzar 10 segundos
        mediaPlayer.seekTo(minOf(newPosition, mediaPlayer.duration))
    }

    // Función para retroceder 10 segundos
    private fun rewind() {
        val newPosition = mediaPlayer.currentPosition - 10000 // Retroceder 10 segundos
        mediaPlayer.seekTo(maxOf(newPosition, 0))
    }

    private val updateSeekBar = object : Runnable {
        override fun run() {
            binding.seekBar.progress = mediaPlayer.currentPosition
            binding.seekBar.postDelayed(this, 1000)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.release()
        _binding = null
    }
}
