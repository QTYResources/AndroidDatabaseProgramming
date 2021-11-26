package jwei.apps.dataforandroid.ch1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder

class InternalStorageExample2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // THE NAME OF THE FILE
        val fileName = "my_file.txt"

        try {
            val fis = openFileInput(fileName)
            val isr = InputStreamReader(fis)

            // READ STRING OF UNKNOWN LENGTH
            val sb = StringBuilder()
            val inputBuffer = charArrayOf()
            var l = isr.read(inputBuffer)
            while (l != -1) {
                sb.append(inputBuffer, 0, 1)
                l = isr.read(inputBuffer)
            }

            // CONVERT BYTES TO STRING
            val readString = sb.toString()
            Log.i("LOG_TAG", "Read string: $readString")

            // CAN ALSO DELETE THE FILE
            deleteFile(fileName)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}