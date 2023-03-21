package com.mihai.takepicandupload

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface NovaAPI {

    @Multipart
    @POST("UploadPic")
    suspend fun uploadFile(
        @Part filePart: MultipartBody.Part,
        @Part("filename") filename: RequestBody
    ): UploadResponse
}