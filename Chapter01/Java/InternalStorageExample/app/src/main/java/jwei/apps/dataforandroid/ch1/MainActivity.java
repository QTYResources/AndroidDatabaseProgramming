package jwei.apps.dataforandroid.ch1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // THE NAME OF THE FILE
        String fileName = "my_file.txt";

        // STRING TO BE WRITTEN TO FILE
        String msg = "Hello World.";
        try {
            // CREATE THE FILE AND WRITE
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(msg.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}