package jwei.apps.dataforandroid.ch1

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

class ExternalStorageExample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val fileName = "my_file.txt"
        val msg = "Hello World."

        var externalAvailable = false
        var externalWriteable = false
        val state = Environment.getExternalStorageState()

        if (state == Environment.MEDIA_MOUNTED) {
            // HERE MEDIA IS BOTH AVAILABLE AND WRITEABLE
            externalAvailable = true
            externalWriteable = true
        } else if (state == Environment.MEDIA_MOUNTED_READ_ONLY) {
            // here SD CARD IS AVAILABLE BUT NOT WRITEABLE
            externalAvailable = true
        } else {
            // HERE FAILURE COULD BE RESULT OF MANY SITUATIONS
            // NO OP
        }

        if (externalAvailable && externalWriteable) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    Log.d("LOG_TAG", "No write external storage permission.")
                    return
                }
            }
            // FOR API LEVEL 7 AND BELOW - RETRIEVE SD CARD DIRECTORY
            val root = Environment.getExternalStorageDirectory()
            val f = File(root, fileName)
            if (!f.exists()) {
                f.createNewFile()
            }
            Log.d("LOG_TAG", "path: ${f.absolutePath}")
            try {
                // NOTE THIS IS DIFFERENT FROM INTERNAL STORAGE WRITER
                val fWriter = FileWriter(f)
                val out = BufferedWriter(fWriter)
                out.write(msg)
                out.close()
            } catch (e: IOException) {
                Log.e("LOG_TAG", "error: ", e)
            }
        } else {
            Log.e("LOG_TAG", "SD CARD UNAVAILABLE")
        }
    }

}