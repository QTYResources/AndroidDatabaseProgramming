package jwei.apps.dataforandroid.ch1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SharedPreferenceExample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sp = getSharedPreferences(MY_DB, Context.MODE_PRIVATE)

        val e = sp.edit()
        e.putString("testStringKey", "Hello World")
        e.putBoolean("testBooleanKey", true)
        e.commit()

        val stringValue = sp.getString("testStringKey", "error")
        val booleanValue = sp.getBoolean("testBooleanKey", false)

        Log.i(TAG, "Retrieved string value: $stringValue")
        Log.i(TAG, "Retrieved boolean value: $booleanValue")
    }

    companion object {
        const val MY_DB = "my_db"
        const val TAG = "SPE"
    }
}