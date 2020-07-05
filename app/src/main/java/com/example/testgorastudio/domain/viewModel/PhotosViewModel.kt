package com.example.testgorastudio.domain.viewModel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testgorastudio.ui.main.MainApplication
import kotlinx.coroutines.*

class PhotosViewModelFactory(private val application: MainApplication) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotosViewModel::class.java)) {
            return PhotosViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class PhotosViewModel(private val application: MainApplication) : AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var userId : Int = -1
    val photos by lazy {
        application.photosRepository.photos
    }

    fun fetchPhotos(userId : Int) {
        if(this.userId != userId) {
            this.userId = userId
            coroutineScope.launch {
                application.photosRepository.fetchPhotos(userId)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun clearPhotos() {
        userId = -1
        application.photosRepository.clearPhotos()
    }
}