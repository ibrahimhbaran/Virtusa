package com.app.virtusa.views

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager

import com.app.virtusa.R
import com.app.virtusa.data.source.AlbumsRepository
import com.app.virtusa.databinding.ActivityMainBinding
import com.app.virtusa.viewModel.AlbumViewModel


class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

    private val albumAdapter = AlbumAdapter(this)

    private val albumViewModel = AlbumViewModel()

    private var layoutManager = LinearLayoutManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.adapter = albumAdapter
        binding.albumViewModel = albumViewModel
        binding.layoutManager = layoutManager
        binding.dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.setLifecycleOwner(this)

        albumViewModel.loadAlbums(AlbumsRepository.getInstance())


    }


}
