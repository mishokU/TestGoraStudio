package com.example.testgorastudio.data.remote.dataSources

import com.example.testgorastudio.data.remote.retrofit.JsonService

class UsersDataSource(private val usersService : JsonService) {

    fun fetchUsersAsync() = usersService.getUsersAsync()

}