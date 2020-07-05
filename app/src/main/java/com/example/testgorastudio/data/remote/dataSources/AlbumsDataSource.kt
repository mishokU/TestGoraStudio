package com.example.testgorastudio.data.remote.dataSources

import com.example.testgorastudio.data.remote.retrofit.JsonService

class AlbumsDataSource(private val albumsService : JsonService) {

    fun fetchAlbumsAsync(albumId : Int?) = albumsService.getAlbumsAsync(albumId)

}