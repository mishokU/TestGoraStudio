package com.example.testgorastudio.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testgorastudio.data.converter.asUIModel
import com.example.testgorastudio.data.remote.dataSources.UsersDataSource
import com.example.testgorastudio.domain.models.UserUIModel
import com.example.testgorastudio.domain.utils.Result
import java.lang.Exception

class UsersRepository(private val usersDataSource: UsersDataSource) {

    private val _users = MutableLiveData<Result<List<UserUIModel>>>()
    val users : LiveData<Result<List<UserUIModel>>>
        get() = _users

    suspend fun refreshUsers() {
        val getUsersDeferred = usersDataSource.fetchUsersAsync()
        try {
            _users.value = Result.Loading
            val users = getUsersDeferred.await()
            if (users.isNotEmpty()) {
                _users.value = Result.Success(users.asUIModel())
            }
        } catch (e: Exception) {
            _users.value = Result.Error(e)
        }
    }

}
