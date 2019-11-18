package com.example.paylocitychallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Application home page.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStartEmployeeInformationActivity(View view) {
        Intent employeeInformationIntent = new Intent(MainActivity.this, EmployeeInformationActivity.class);
        startActivity(employeeInformationIntent);
    }
}
