package com.example.application.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.application.R;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.application.R;

public class NotificationDetails extends AppCompatActivity {

    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);

        userName = findViewById(R.id.textview_userName);

        String s = getIntent().getStringExtra("username");
        String userdescription=getIntent().getStringExtra("username");

        userName.setText(s + userdescription);

    }
}