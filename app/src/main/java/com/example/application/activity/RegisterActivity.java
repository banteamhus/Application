package com.example.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.application.R;
import com.example.application.account.Date;
import com.example.application.account.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    EditText edtEmail, edtUsername, edtTendn,edtPassword, edtPassword2;
    Button btnDk, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AnhXa();
        mAuth = FirebaseAuth.getInstance();
        btnDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccountbyEmail_Pass();

            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void createAccountbyEmail_Pass() {
        final String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String password2 = edtPassword2.getText().toString();
        if (password.equals(password2)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                final FirebaseUser userFB = task.getResult().getUser();
                                if (userFB != null) {
                                    //Gửi 1 email xác thực tài khoản
                                    userFB.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                //Tiến hành thông tin user vào Database
                                                User users = new User();
                                                users.setId(mAuth.getUid());
                                                users.setName(edtUsername.getText().toString());
                                                users.setGmail(email);
                                                Date date = new Date();
                                             //   users.setDate(date); // chưa sửa được cái layout
                                                users.setGender(false); // chưa sửa đc layout
                                                myRef.child("users").child(users.getId())
                                                        .setValue(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        // Write was successful!
                                                        // ...
                                                    }
                                                })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                // Write failed
                                                                // ...
                                                            }
                                                        });
                                                // xong

                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
            Toast.makeText(RegisterActivity.this,"Tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(RegisterActivity.this,"Tạo tài khoản thất bại",Toast.LENGTH_SHORT).show();
        }
    }


    private void AnhXa(){
        edtEmail =(EditText) findViewById(R.id.edt_register_Email);
        edtUsername=(EditText) findViewById(R.id.edt_register_Tenht);
        edtTendn =(EditText) findViewById(R.id.edt_register_Ngaysinh);
        edtPassword=(EditText) findViewById(R.id.edt_register_Mk);
        edtPassword2=(EditText) findViewById(R.id.edt_register_Nhaplai);
        btnDk=(Button) findViewById(R.id.btn_register_dk);
        btnReturn=(Button) findViewById(R.id.btn_register_return);
    }
}