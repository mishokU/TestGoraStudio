package com.example.testgorastudio.domain.viewModel

import androidx.lifecycle.*
import com.example.testgorastudio.domain.models.UserUIModel
import com.example.testgorastudio.domain.utils.Result
import com.example.testgorastudio.ui.main.MainApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UsersViewModelFactory(private val application: MainApplication) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class UsersViewModel(private val application: MainApplication) : AndroidViewModel(application) {

    private val _showUserPhotos = MutableLiveData<UserUIModel>()
    val showUserPhotos: LiveData<UserUIModel>
        get() = _showUserPhotos

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val users : LiveData<Result<List<UserUIModel>>> = application.repository.users

    init {
        refreshUsers()
    }

    fun refreshUsers() = coroutineScope.launch {
        application.repository.refreshUsers()
    }

    fun showUserPhotos(user : UserUIModel?){
        _showUserPhotos.value = user
    }

    fun onUserPhotosComplete(){
        _showUserPhotos.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}