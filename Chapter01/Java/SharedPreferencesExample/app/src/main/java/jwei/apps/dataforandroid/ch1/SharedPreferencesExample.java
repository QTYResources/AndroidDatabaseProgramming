package jwei.apps.dataforandroid.ch1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

/**
 * Examples for initial case as defined in Chapter 1 of Data for Android.
 *
 * @author Jason Wei
 *
 */
public class SharedPreferencesExample extends AppCompatActivity {

    private static final String MY_DB = "my_db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences(MY_DB, Context.MODE_PRIVATE);

        SharedPreferences.Editor e = sp.edit();
        e.putString("testStringKey", "Hello World");
        e.putBoolean("testBooleanKey", true);
        e.commit();

        String stringValue = sp.getString("testStringKey", "error");
        boolean booleanValue = sp.getBoolean("testBooleanKey", false);

        Log.i("SPE", "Retrieved string value: " + stringValue);
        Log.i("SPE", "Retrieved boolean value: " + booleanValue);
    }
}