package jwei.apps.dataforandroid.ch1;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExternalStorageExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fileName = "my_file.txt";
        String msg = "Hello World.";

        boolean externalAvailable = false;
        boolean externalWriteable = false;
        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // HERE MEDIA IS BOTH AVAILABLE AND WRITEABLE
            externalAvailable = true;
            externalWriteable = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            // HERE SD CARD IS AVAILABLE BUT NOT WRITEABLE
            externalAvailable = true;
        } else {
            // HERE FAILURE COULD BE RESULT OF MANY SITUATIONS
            // NO OP
        }

        if (externalAvailable && externalWriteable) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
            // FOR API LEVEL 7 AND BELOW - RETRIEVE SD CARD DIRECTORY
            File root = Environment.getExternalStorageDirectory();
            File f = new File(root, fileName);
            try {
                // NOTE THIS IS DIFFERENT FROM INTERNAL STORAGE WRITER
                FileWriter fWriter = new FileWriter(f);
                BufferedWriter out = new BufferedWriter(fWriter);
                out.write(msg);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("LOG_TAG", "SD CARD UNAVAILABLE");
        }
    }
}