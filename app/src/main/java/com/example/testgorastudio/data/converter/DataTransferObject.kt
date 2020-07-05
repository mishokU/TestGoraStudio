package com.example.testgorastudio.data.converter

import com.example.testgorastudio.data.remote.models.PhotoModel
import com.example.testgorastudio.data.remote.models.UserModel
import com.example.testgorastudio.domain.models.PhotoUIModel
import com.example.testgorastudio.domain.models.UserUIModel

fun List<UserModel>.asUIModel(): List<UserUIModel> {
    return map {
        UserUIModel(
            id = it.id,
            name = it.name
        )
    }
}

fun List<PhotoModel>.asPhotoUIModel(): List<PhotoUIModel> {
    return map {
        PhotoUIModel(
            albumId = it.albumId,
            id = it.id,
            title = it.title,
            url = it.url
        )
    }
}