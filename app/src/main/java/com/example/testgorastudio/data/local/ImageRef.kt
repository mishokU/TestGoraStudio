package com.example.testgorastudio.data.local

import android.graphics.Bitmap

data class ImageRef(
    val bitmap: Bitmap,
    var refsCount: Int = 0
)