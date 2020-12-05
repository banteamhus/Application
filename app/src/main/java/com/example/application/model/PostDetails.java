package com.example.application.model;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application.R;

public class PostDetails extends AppCompatActivity {

    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        userName = findViewById(R.id.textview_userName);

        String s=getIntent().getStringExtra("username");
        String userdescription=getIntent().getStringExtra("username");

        userName.setText(s + userdescription);

    }
}