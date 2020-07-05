package com.example.testgorastudio.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testgorastudio.data.repo.ImagesRepository
import com.example.testgorastudio.databinding.PhotoItemBinding
import com.example.testgorastudio.domain.models.PhotoUIModel
import com.google.android.material.button.MaterialButton

class PhotosRecyclerViewAdapter : ListAdapter<PhotoUIModel, PhotosRecyclerViewAdapter.PhotoViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<PhotoUIModel>() {

        override fun areItemsTheSame(oldItem: PhotoUIModel, newItem: PhotoUIModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotoUIModel, newItem: PhotoUIModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PhotoViewHolder {
        return PhotoViewHolder(
            PhotoItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int){
        val photo = getItem(position)
        holder.setIsRecyclable(false)
        holder.getImagePhoto(photo.url)
        holder.bind(photo)
    }

    class PhotoViewHolder(private val binding: PhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun getImagePhoto(url: String) {
            val image = binding.photoImage
            val progress = binding.imageProgress
            val reloadImgBtn = binding.reloadImageBtn
            loadImage(url,image, progress,reloadImgBtn)
            reloadImgBtn.setOnClickListener {
                loadImage(url, image, progress, reloadImgBtn)
            }
        }

        private fun loadImage(
            url: String,
            image: ImageView,
            progress: ProgressBar,
            reloadImgBtn: MaterialButton
        ) {
            ImagesRepository.fetchImage(url,image,progress,reloadImgBtn)
        }

        fun bind(photo: PhotoUIModel?) {
            binding.photo = photo
            binding.executePendingBindings()
        }
    }
}