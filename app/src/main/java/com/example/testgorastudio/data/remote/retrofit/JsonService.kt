package com.example.testgorastudio.data.remote.retrofit

import com.example.testgorastudio.data.remote.models.AlbumModel
import com.example.testgorastudio.data.remote.models.PhotoModel
import com.example.testgorastudio.data.remote.models.UserModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonService {

    @GET("/users")
    fun getUsersAsync() : Deferred<List<UserModel>>

    @GET("/albums")
    fun getAlbumsAsync(@Query("userId")userId : Int?) : Deferred<List<AlbumModel>>

    @GET("/photos")
    fun getPhotosAsync(@Query("albumId")albumId : Int?) : Deferred<List<PhotoModel>>

}