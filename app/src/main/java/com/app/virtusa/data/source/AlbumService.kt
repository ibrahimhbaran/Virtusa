package com.app.virtusa.data.source

import com.app.virtusa.data.model.Album
import io.reactivex.Single
import retrofit2.http.GET

interface AlbumService {
    @GET("albums")
    fun getAlbums(): Single<List<Album>>
}