package com.example.testgorastudio.ui.main

import android.app.Application
import com.example.testgorastudio.data.remote.dataSources.AlbumsDataSource
import com.example.testgorastudio.data.remote.dataSources.PhotosDataSource
import com.example.testgorastudio.data.remote.dataSources.UsersDataSource
import com.example.testgorastudio.data.remote.retrofit.JsonApi
import com.example.testgorastudio.data.repo.PhotosRepository
import com.example.testgorastudio.data.repo.UsersRepository

class MainApplication : Application() {

    private val usersDataSource = UsersDataSource(JsonApi.retrofitService)

    private val photosDataSource = PhotosDataSource(JsonApi.retrofitService)
    private val albumsDataSource = AlbumsDataSource(JsonApi.retrofitService)

    val repository = UsersRepository(usersDataSource)
    val photosRepository = PhotosRepository(photosDataSource, albumsDataSource)

}