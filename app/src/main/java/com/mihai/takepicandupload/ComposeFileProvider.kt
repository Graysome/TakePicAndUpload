package com.mihai.takepicandupload

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.util.*

class ComposeFileProvider : FileProvider() {
    companion object {
        fun getImageUri(context: Context): Uri {
            val directory = File(context.filesDir, "NovaPics")
            directory.mkdirs()

            val fileName = "test_" + UUID.randomUUID().toString() + ".jpg"
            val file = File(directory,fileName)
            val authority = context.packageName + ".fileprovider"
            return getUriForFile(                context,
                authority,
                file,
            )
        }
    }
}