package com.example.livesound.ui.player
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.livesound.R

class PlayerFragment : Fragment() {
    private val playerView: PlayerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mediaUri = "android.resource://${requireContext().packageName}/${R.raw.billie720}" // Default
        /*val mediaUri = arguments?.getString(ARG_MEDIA_ITEM)
            ?: "android.resource://${requireContext().packageName}/${R.raw.billie720}" // Default*/
        playerView.getPlayer()
        playerView.prepareAndPlay(mediaUri)
    }
}