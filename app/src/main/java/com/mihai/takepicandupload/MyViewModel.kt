package com.mihai.takepicandupload

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val novaAPI: NovaAPI): ViewModel() {

    fun testViewModel(ctx: Context){

        Log.d("uploadDebug", "testViewModel: Showing list of files:")
        val files = File(ctx.filesDir, "NovaPics").listFiles()

        for (file in files!!){
            Log.d("uploadDebug", "testViewModel: ${file.name}")

            val filename = file.name

            val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
            val filenameBody = RequestBody.create(MediaType.parse("multipart/form-data"), filename)

            viewModelScope.launch {
                try {
                    val uploadResponse = novaAPI.uploadFile(body, filenameBody)
                    Log.d(
                        "uploadDebug",
                        "testViewModel: Code: ${uploadResponse.Code} | Message ${uploadResponse.Message}"
                    )
                } catch (ex: Exception) {
                    Log.d("uploadDebug", "testViewModel: Error ${ex.message.toString()}")
                }
            }

        }
    }

}