package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("bcvt", "onCreate");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("bcvt", "onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("bcvt", "onStart");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("bcvt", "onStop");
    }

}