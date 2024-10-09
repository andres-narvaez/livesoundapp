package com.example.livesound.ui.playlist

import com.example.livesound.ui.player.PlayerViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.livesound.R
import com.example.livesound.databinding.FragmentPlaylistBinding
import com.example.livesound.ui.profile.Song
import com.example.livesound.ui.profile.SongAdapter


class PlaylistFragment : Fragment() {

    private var _binding: FragmentPlaylistBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val PlaylistViewModel =
            ViewModelProvider(this).get(PlaylistViewModel::class.java)

        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPlaylist
        PlaylistViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val songList = listOf(
            Song(R.drawable.song_image1, "Don't smile at me", "Billie Eilish", "5:30"),
            Song(R.drawable.song_image2, "As it was", "Harry Styles", "4:10"),
            Song(R.drawable.song_image1, "Don't smile at me", "Billie Eilish", "5:30"),
            Song(R.drawable.song_image2, "As it was", "Harry Styles", "4:10"),
            Song(R.drawable.song_image1, "Don't smile at me", "Billie Eilish", "5:30"),
            Song(R.drawable.song_image2, "As it was", "Harry Styles", "4:10"),
            Song(R.drawable.song_image1, "Don't smile at me", "Billie Eilish", "5:30"),
            Song(R.drawable.song_image2, "As it was", "Harry Styles", "4:10"),
            Song(R.drawable.song_image1, "Don't smile at me", "Billie Eilish", "5:30"),
            Song(R.drawable.song_image2, "As it was", "Harry Styles", "4:10"),
            Song(R.drawable.song_image1, "Don't smile at me", "Billie Eilish", "5:30"),
            Song(R.drawable.song_image2, "As it was", "Harry Styles", "4:10"),
            // Añadir más canciones aquí
        )

        val adapterSongs = SongAdapter(songList) { song ->
            // Manejar la acción de reproducción aquí
            println(song)
            findNavController().navigate(R.id.action_playlistFragment_to_playerFragment)
        }

        binding.recyclerViewSongs.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewSongs.adapter = adapterSongs
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}