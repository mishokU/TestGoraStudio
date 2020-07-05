package com.example.testgorastudio.domain.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

/*
    Тут я пытался вместо Picasso загрузить картинки, также пытался через Coroutines
    Но мне выдавало ошибку, что файл не найден, так и не смог понять почему, но оставил этот код,
    как попытку другого решения.
 */

class BitmapLoader(private val imageView: ImageView) : AsyncTask<String,Void, Bitmap>() {

    override fun doInBackground(vararg params: String?): Bitmap? {
        val url: String? = params[0]
        var bitmap: Bitmap? = null
        try {
            val urlU = URL(url)
            val connection : HttpsURLConnection= urlU.openConnection() as HttpsURLConnection
            connection.doInput = true
            connection.connect()
            val inputStream: InputStream? = connection.inputStream
            bitmap = BitmapFactory.decodeStream(inputStream)

        } catch (ioException: Exception) {
            ioException.printStackTrace()
        }
        return bitmap
    }

    override fun onPostExecute(result: Bitmap?) {
        if(result != null){
            imageView.setImageBitmap(result)
        }
    }
}