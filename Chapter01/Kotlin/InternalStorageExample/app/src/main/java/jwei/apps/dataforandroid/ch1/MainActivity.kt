package jwei.apps.dataforandroid.ch1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // THE NAME OF THE FILE
        var fileName = "my_file.txt"

        // STRING TO BE WRITTEN TO FILE
        val msg = "Hello World."
        try {
            // CREATE THE FILE AND WRITE
            val fos = openFileOutput(fileName, Context.MODE_PRIVATE)
            fos.write(msg.toByteArray())
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}