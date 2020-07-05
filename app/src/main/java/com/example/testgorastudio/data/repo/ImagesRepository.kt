package com.example.testgorastudio.data.repo

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.testgorastudio.data.local.ImageRef
import com.example.testgorastudio.data.local.MemoryCache
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

object ImagesRepository {

    fun fetchImage(url: String, image: ImageView, progress: ProgressBar, reloadImgBtn: MaterialButton) {
        if(!MemoryCache.contains(url)){
            Picasso.get().load(url)
                .memoryPolicy(MemoryPolicy.NO_STORE, MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .into(object : Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                        progress.visibility = View.VISIBLE
                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                        progress.visibility = View.GONE
                        reloadImgBtn.visibility = View.VISIBLE
                        image.setImageBitmap(null)
                    }

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        progress.visibility = View.GONE
                        reloadImgBtn.visibility = View.GONE
                        if(bitmap != null){
                            MemoryCache.put(url, ImageRef(bitmap, 1))
                            image.setImageBitmap(bitmap)
                        }
                    }
                })
        } else {
            progress.visibility = View.GONE
            image.setImageBitmap(MemoryCache.get(url))
            MemoryCache.updateRef(url)
        }
    }
}