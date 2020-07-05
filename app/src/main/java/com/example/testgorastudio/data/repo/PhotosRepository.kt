package com.example.testgorastudio.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testgorastudio.data.converter.asPhotoUIModel
import com.example.testgorastudio.data.remote.dataSources.AlbumsDataSource
import com.example.testgorastudio.data.remote.dataSources.PhotosDataSource
import com.example.testgorastudio.domain.models.PhotoUIModel
import com.example.testgorastudio.domain.utils.Result
import java.lang.Exception

class PhotosRepository(
    private val photosDataSource: PhotosDataSource,
    private val albumsDataSource : AlbumsDataSource
) {

    private val _photos = MutableLiveData<Result<List<PhotoUIModel>>>()
    val photos : LiveData<Result<List<PhotoUIModel>>>
        get() = _photos

    suspend fun fetchPhotos(userId : Int?) {
        val getAlbumsDeferred = albumsDataSource.fetchAlbumsAsync(userId)
        try {
            _photos.value = Result.Loading
            val albums = getAlbumsDeferred.await()

            val photos : MutableList<PhotoUIModel> = mutableListOf()
            for(album in albums){
                val getUsersDeferred = photosDataSource.fetchPhotosAsync(album.id)
                photos.addAll(getUsersDeferred.await().asPhotoUIModel())
            }

            if(photos.isNotEmpty()){
                _photos.value = Result.Success(photos)
            }
        } catch (e : Exception){
            _photos.value = Result.Error(e)
        }
    }

    fun clearPhotos(){
        _photos.value = null
    }

}
