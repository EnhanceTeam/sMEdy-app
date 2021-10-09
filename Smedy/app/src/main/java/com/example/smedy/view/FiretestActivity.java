package com.example.smedy.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.smedy.R;

public class FiretestActivity extends AppCompatActivity {

    private TextView testTextViewUsername, testTextViewEmail;
    private Button testButtonSignOut;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firetest);

        initialize();

    }

    private void initialize() {
        testTextViewUsername = findViewById(R.id.testTextViewUsername);
        testTextViewEmail = findViewById(R.id.testTextViewEmail);
        testButtonSignOut = findViewById(R.id.testButtonSignOut);

    }
}