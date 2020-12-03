package com.example.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.application.R;
import com.example.application.account.User;
import com.example.application.update_account.DateActivity;
import com.example.application.update_account.IDActivity;
import com.example.application.update_account.NameActivity;
import com.example.application.update_account.PasswordActivity;
import com.example.application.update_account.SexActivity;
import com.example.application.update_account.UserActivity;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountActivity extends AppCompatActivity {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    TextView userid,usergmail,username,usergender,userdate;
    ImageView avatar;
    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private User user1 = new User();
    Button deleteAccount;
    Button logoutAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Anhxa();
        userid =findViewById(R.id.textview_userid);
        usergmail=findViewById(R.id.textview_tendangnhap);
        username=findViewById(R.id.textview_tentaikhoan);
        avatar=findViewById(R.id.img_avatar);
        usergender =findViewById(R.id.textview_gioitinh);
        userdate = findViewById(R.id.textview_ngaysinh);
        deleteAccount=findViewById(R.id.button_deleteAccount);
        //logoutAccount=findViewById(R.id.button_logout);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        if(user != null) {
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    user1 = dataSnapshot.getValue(User.class);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
            String photoURL = user.getPhotoUrl().toString();
            String name = user.getDisplayName();
            String gmail = user.getEmail();
            String date = user1.getDate();
            boolean gender = user1.getGender();
            Glide.with(this).load(photoURL).into(avatar);
            userid.setText(user.getUid());
            username.setText(name);
            usergmail.setText(gmail);
            userdate.setText(date);
            if(gender == false) usergender.setText("Nữ");
            else usergender.setText("Nam");
        }
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    LoginManager.getInstance().logOut();
// ...
                                    mDatabase.removeValue();
                                    Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(AccountActivity.this,"Xóa tài khoản thành công",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, IDActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, PasswordActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, NameActivity.class);
                startActivity(intent);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, SexActivity.class);
                startActivity(intent);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, DateActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Anhxa(){
        btn1=findViewById(R.id.btn_account_id);
        btn2=findViewById(R.id.btn_account_user);
        btn3=findViewById(R.id.btn_account_pw);
        btn4=findViewById(R.id.btn_account_name);
        btn5=findViewById(R.id.btn_account_sex);
        btn6=findViewById(R.id.btn_account_date);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}