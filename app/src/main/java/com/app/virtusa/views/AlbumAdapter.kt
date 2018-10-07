package com.app.virtusa.views

import android.view.LayoutInflater
import android.view.ViewGroup


import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import com.app.virtusa.R
import com.app.virtusa.data.model.Album
import com.app.virtusa.databinding.ItemAlbumBinding


/**
 * Adapter for the album list
 * @property context Context in which the application is running
 */
class AlbumAdapter(private val context: Context) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {


    private var albums: List<Album> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemAlbumBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_album, parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(p0: AlbumViewHolder, position: Int) {
        p0.bind(albums[position])
    }


    override fun getItemCount(): Int {
        return albums.size
    }


    /**
     * Updates the list of albums of the adapter
     * @param albums the new list of albums of the adapter
     */
    fun updateAlbums(albums: List<Album>?) {
        albums?.let {
            this.albums = it
            notifyDataSetChanged()
        }

    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for album item
     */
    class AlbumViewHolder(private val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a data model  into the view
         */
        fun bind(album: Album) {

            binding.apply {
                setAlbum(album)
            }

        }
    }
}
