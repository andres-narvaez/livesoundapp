package com.example.livesound.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.livesound.databinding.FragmentHomeBinding
import com.example.livesound.R
import com.example.livesound.ui.profile.Playlist
import com.example.livesound.ui.profile.PlaylistAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var itemAdapter: ItemCarouselAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val textViewList: TextView = binding.textList
        homeViewModel.textList.observe(viewLifecycleOwner) {
            textViewList.text = it
        }

        val songList = listOf(
            Song(R.drawable.song_carousel1, "Don't Smile at Me", "Billie Eilish"),
            Song(R.drawable.song_carousel2, "As It Was", "Harry Styles"),
            Song(R.drawable.song_carousel3, "Blinding Lights", "The Weeknd"),
            Song(R.drawable.song_carousel1, "Don't Smile at Me", "Billie Eilish"),
            Song(R.drawable.song_carousel2, "As It Was", "Harry Styles"),
            Song(R.drawable.song_carousel3, "Blinding Lights", "The Weeknd")
        )

        itemAdapter = ItemCarouselAdapter(songList){ song ->
            // Manejar la acción de reproducción aquí
            println(song)
            findNavController().navigate(R.id.action_home_to_playerFragment)

        }
        binding.carouselRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.carouselRecyclerview.adapter = itemAdapter

        val playList = listOf(
            Playlist(R.drawable.video_image1, "Super Freaky Girl", "Nicki Minaj", "5:33"),
            Playlist(R.drawable.video_image2, "Bad Habit", "Stive Lacy", "4:10"),
            // Añadir más canciones aquí
        )

        val adapterPlaylist = PlaylistAdapter(playList) { playlist ->
            // Manejar la acción de reproducción aquí
            println(playlist)
            findNavController().navigate(R.id.action_home_to_playListFragment)
        }

        binding.recyclerViewPlaylistItems.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewPlaylistItems.adapter = adapterPlaylist
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}