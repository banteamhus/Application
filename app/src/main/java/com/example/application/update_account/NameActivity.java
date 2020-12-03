package com.example.application.update_account;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application.R;

public class NameActivity extends AppCompatActivity {

    Button btnReturn;
    EditText edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        btnReturn=findViewById(R.id.btn_name);
        edtName=findViewById(R.id.edt_name);


        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        edtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtName.getText().clear();
            }
        });

    }
}