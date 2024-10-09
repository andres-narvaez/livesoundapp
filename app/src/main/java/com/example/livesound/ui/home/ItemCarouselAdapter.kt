package com.example.livesound.ui.home
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.livesound.R
import com.example.livesound.ui.profile.Playlist

// Datos de una canción
data class Song(
    val imageResId: Int, // Imagen del artista o álbum
    val songName: String,
    val artistName: String
)

// Interfaz para manejar el evento de clic en el botón de reproducción
interface OnPlayButtonClickListener {
    fun onPlayClick(song: Song)
}

class ItemCarouselAdapter(
    private val songList: List<Song>,
    private val playButtonClickListener: (Song) -> Unit
) : RecyclerView.Adapter<ItemCarouselAdapter.SongViewHolder>() {

    // ViewHolder que vincula la vista del ítem
    class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val artistImage: ImageView = view.findViewById(R.id.artist_image)
        val songName: TextView = view.findViewById(R.id.song_name)
        val artistName: TextView = view.findViewById(R.id.artist_name)
        val playButton: ImageButton = view.findViewById(R.id.play_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songList[position]
        holder.artistImage.setImageResource(song.imageResId)
        holder.songName.text = song.songName
        holder.artistName.text = song.artistName

        // Evento de clic en el botón de reproducción
        holder.playButton.setOnClickListener {
            playButtonClickListener(song)
        }
    }

    override fun getItemCount(): Int {
        return songList.size
    }
}
