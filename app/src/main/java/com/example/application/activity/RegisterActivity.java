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
import com.example.application.account.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



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
                checkEmailExistsOrNot();

            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    void checkEmailExistsOrNot(){
        int n =0;
        mAuth.fetchSignInMethodsForEmail(edtEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                if (task.getResult().getSignInMethods().size() == 0){
                    // email not existed
                    createAccountbyEmail_Pass();
                }else {
                    // email existed
                    Toast.makeText(RegisterActivity.this, "Tài khoản gmail đã tồn tại", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void createAccountbyEmail_Pass() {
        final String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String password2 = edtPassword2.getText().toString();
        final String name = edtUsername.getText().toString();
        // thêm điều kiện
        if (password.equals(password2) && name != null  ) {
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
                                                users.setName(name);
                                                users.setGmail(email);
                                                users.setRole(0);
                                                users.setPhotoUrl("https://ephoto360.com/uploads/worigin/2020/03/23/tao-avatar-mac-dinh-facebook-thay-nen-cuc-hot5e7838ae39057_96eb8aef68a3aa00523448390b49fbcb.jpg");
                                                users.setDate(edtTendn.getText().toString()); // chưa sửa được cái layout
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
                                                Toast.makeText(RegisterActivity.this,"Tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                                startActivity(intent);
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