package com.example.myrappi.ui.util

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.TypeConverter
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class Converters :AppCompatActivity(){


    @TypeConverter
    fun fromBitmap(bitmap: Bitmap):ByteArray{
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return  outputStream.toByteArray()
    }



    @TypeConverter
    fun toBitmap(byteArray: ByteArray):Bitmap{
        return BitmapFactory.decodeByteArray(byteArray,0, byteArray.size)
    }

    var image: Bitmap? = null
     fun getBitmapFromURL(src: String?) : Bitmap? {

            try {
                val policy = ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
                val url = URL(src)
                val bitMap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                 image = Bitmap.createScaledBitmap(bitMap, 500, 750, true)
            } catch (e: IOException) {
                // Log exception
            }
        return image
    }

     fun mLoad(string: String) :Bitmap?{

         AsyncTask.execute {
             try {

                 val policy = ThreadPolicy.Builder().permitAll().build()
                 StrictMode.setThreadPolicy(policy)
                 val url = URL(string)
                 var urlConnection: HttpURLConnection? = null
                 urlConnection = url.openConnection() as HttpURLConnection
                 val `in`: InputStream =
                     BufferedInputStream(urlConnection!!.inputStream)
                 val bufferedInputStream = BufferedInputStream(`in`)
                 image = BitmapFactory.decodeStream(bufferedInputStream)
                 mSaveMediaToStorage(image,this)
                 Log.d(TAG, "get json: $`in`")
                 urlConnection!!.disconnect()
             } catch (e: IOException) {
                 e.printStackTrace()
             }

         }
         return image
     }

    fun mSaveMediaToStorage(bitmap: Bitmap?,context: Context) {
        try {
            getResizedBitmap(bitmap,500)
            val filename = "${System.currentTimeMillis()}.jpg"
            var fos: OutputStream? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                context.contentResolver?.also { resolver ->
                    val contentValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    }
                    val imageUri: Uri? =
                        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                    fos = imageUri?.let { resolver.openOutputStream(it) }
                }
            } else {
                val imagesDir =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                val image = File(imagesDir, filename)
                fos = FileOutputStream(image)
            }
            fos?.use {
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
                Toast.makeText(this, "Saved to Gallery", Toast.LENGTH_SHORT).show()
            }
        }catch (e :Exception)
        {
                e.toString()
         }
    }

    fun getResizedBitmap(image: Bitmap?, maxSize: Int): Bitmap? {
        var width = image?.width
        var height = image?.height
        val bitmapRatio = width?.toFloat()!! / height?.toFloat()!!
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image!!, width, height, true)
    }

    fun video(context: Context)
    {



        try
        {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val u = URL("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4")
            val `is` = u.openStream()
            val dis = DataInputStream(`is`)
            val buffer = ByteArray(1024)

            var length: Int
            var fos: OutputStream? = null
            while (dis.read(buffer).also { length = it } > 0) {

                    fos?.write(buffer, 0, length)

            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                context.contentResolver?.also { resolver ->
                    val contentValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, "videotest")
                        put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
                        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    }
                    val imageUri: Uri? =
                        resolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)
                    fos = imageUri?.let { resolver.openOutputStream(it) }
                }
                } else {
                    val imagesDir =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    val image = File(imagesDir, "filename")
                    fos = FileOutputStream(image)
                }
                fos?.use {
                    //bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
                    //fos.write()
                    // flushing output
                    fos?.flush();

                    // closing streams
                    fos?.close();
                    Toast.makeText(this, "Saved to Gallery", Toast.LENGTH_SHORT).show()
                }
        }catch (e :Exception)
        {
            e.toString()
        }

         catch (mue: MalformedURLException) {
            Log.e("SYNC getUpdate", "malformed url error", mue)
        } catch (ioe: IOException) {
            Log.e("SYNC getUpdate", "io error", ioe)
        } catch (se: SecurityException) {
            Log.e("SYNC getUpdate", "security error", se)
        }

    }

}