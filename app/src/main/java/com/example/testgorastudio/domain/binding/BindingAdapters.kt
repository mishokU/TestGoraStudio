package com.example.testgorastudio.domain.binding

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testgorastudio.domain.models.PhotoUIModel
import com.example.testgorastudio.domain.models.UserUIModel
import com.example.testgorastudio.domain.utils.Result
import com.example.testgorastudio.ui.photos.PhotosRecyclerViewAdapter
import com.example.testgorastudio.ui.users.UsersRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_photos.view.*
import kotlinx.android.synthetic.main.fragment_users.view.*

@BindingAdapter("usersList")
fun bindUserList(usersRv : RecyclerView, users : Result<List<UserUIModel>>?){
    when(users){
        is Result.Success -> {
            val adapter = usersRv.adapter as UsersRecyclerViewAdapter
            adapter.submitList(users.data)
        }
    }
}

@BindingAdapter("usersProgress")
fun bindUsersProgress(progressBar: ProgressBar, response : Result<List<UserUIModel>>?){
    progressBar.visibility = if (response == Result.Loading) VISIBLE else INVISIBLE
}

@BindingAdapter("usersErrorHandler")
fun bindUsersError(errorPlace: LinearLayout, response : Result<List<UserUIModel>>?){
    when(response){
        is Result.Error -> {
            errorPlace.userError.text = response.exception.message
            errorPlace.visibility = VISIBLE
        }
        else -> {
            errorPlace.visibility = INVISIBLE
        }
    }
}

@BindingAdapter("photosList")
fun bindPhotosList(photosRv : RecyclerView, response : Result<List<PhotoUIModel>>?){
    val adapter = photosRv.adapter as PhotosRecyclerViewAdapter
    when(response){
        is Result.Success -> {
            adapter.submitList(response.data)
        }
    }
}

@BindingAdapter("errorPhotosHandler")
fun bindPhotosError(errorPlace : LinearLayout, response : Result<List<PhotoUIModel>>?){
    when(response){
        is Result.Error -> {
            errorPlace.error_photos_text.text = response.exception.message
            errorPlace.visibility = VISIBLE
        }
        else -> {
            errorPlace.visibility = INVISIBLE
        }
    }
}

@BindingAdapter("photoProgress")
fun bindPhotosProgress(progressBar: ProgressBar, response : Result<List<PhotoUIModel>>?){
    progressBar.visibility = if(response == Result.Loading) VISIBLE else INVISIBLE
}

@BindingAdapter("loadUrl")
fun bindPhoto(imageView: ImageView, url : String?){
    //Picasso.get().load(url).placeholder(R.drawable.icons8_full_image_32px).into(imageView)
}

