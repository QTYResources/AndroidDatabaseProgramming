package jwei.apps.dataforandroid.ch1

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

/**
 * Examples for use cases as defined in Chapter 1 of Data for Android.
 *
 * @author Jason Wei
 */
class SharedPreferencesExample2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sp = getSharedPreferences(MY_DB, Context.MODE_PRIVATE)

        /**
         * CHECK IF THIS IS USER'S FIRST VIST
         */
        val hasVisited = sp.getBoolean("hasVisited", false)
        if (!hasVisited) {
            // ...
            // SHOW SPLASH ACTIVITY, LOGIN ACTIVITY, ETC
            // ...

            // DON'T FORGET TO COMMIT THE CHANGE
            val e = sp.edit()
            e.putBoolean("hasVisited", true)
            e.commit()
        }

        /**
         * CHECK LAST VISIT TIME
         */
        val lastVisitTime = sp.getLong("lastVisitKey", 0L)
        val timeElapsed = System.currentTimeMillis() - lastVisitTime

        // YOUR UPDATE FREQUENCY HERE
        val UPDATE_FREQ = 1000 * 60 * 60 * 24

        if (timeElapsed > UPDATE_FREQ) {
            // ...
            // PERFORM NECESSARY UPDATES
            // ...
        }

        // STORE LATEST UPDATE TIME
        val e = sp.edit()
        e.putLong("lastVisitKey", System.currentTimeMillis())
        e.commit()

        /**
         * CACHE USER NAME AS STRING
         */
        // TYPICALLY YOU WILL HAVE AN EDIT TEXT VIEW
        // WHERE THE USER ENTERS THEIR USERNAME
        val userNameLoginText = findViewById<EditText>(R.id.login_editText)
        val userName = userNameLoginText.text.toString()

        val e1 = sp.edit()
        e1.putString("userNameCache", userName)
        e1.commit()

        /**
         * REMEBERING A CERTAIN STATE
         */
        val isSilentMode = sp.getBoolean("isSilentRinger", false)
        if (isSilentMode) {
            // ...
            // TURN OFF SOUND
            // ...
        }

        /**
         * caching a location
         */
        // instantiate location manager
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        // ...
        // IGNORE LOCATION LISTENERS
        // ...
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val lastKnownLocation =
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        lastKnownLocation?.let {
            val lat = it.latitude.toFloat()
            val lon = it.longitude.toFloat()

            val e2 = sp.edit()
            e2.putFloat("latitudeCache", lat)
            e2.putFloat("longitudeCache", lon)
            e2.commit()
        }
    }

    companion object {
        const val MY_DB = "my_db"
    }
}