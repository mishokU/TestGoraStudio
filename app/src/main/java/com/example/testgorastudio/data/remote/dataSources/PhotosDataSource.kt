package com.example.testgorastudio.data.remote.dataSources

import com.example.testgorastudio.data.remote.retrofit.JsonService

class PhotosDataSource(private val retrofitService: JsonService) {

    fun fetchPhotosAsync(albumId : Int?) = retrofitService.getPhotosAsync(albumId)

}