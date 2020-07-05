package com.example.testgorastudio.data.local

import android.graphics.Bitmap
import androidx.collection.LruCache

object MemoryCache {

    private val cache: LruCache<String, ImageRef>
    private const val maxImages = 10

    init {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

        // Use 1/8th of the available memory for this memory cache.
        val cacheSize = maxMemory / 8
        cache = object : LruCache<String, ImageRef>(cacheSize) {
            override fun sizeOf(key: String, value: ImageRef): Int {
                return value.bitmap.rowBytes * value.bitmap.height / 1024
            }
        }
    }

    fun put(url: String, imageRef: ImageRef) {
        if(cache.snapshot().size < maxImages){
            if(!contains(url)){
                cache.put(url, imageRef)
            }
        } else {
            removeImage()
            put(url, imageRef)
        }
    }

    private fun removeImage() {
        val min = cache
            .snapshot()
            .minBy {
            it.value.refsCount
        }
        cache.remove(min!!.key)
    }

    fun get(url: String?): Bitmap? {
        if(url != null){
            return cache.get(url)?.bitmap
        }
        return null
    }

    fun contains(url : String?) : Boolean {
        return cache.snapshot().containsKey(url)
    }

    fun clear() {
        cache.evictAll()
    }

    fun updateRef(url: String) {
        cache.snapshot()[url]!!.refsCount += 1
    }

}
