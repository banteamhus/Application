package com.example.application.update_account;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.application.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SexActivity extends AppCompatActivity {

    Button back,save;
    EditText editText;
    private DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser user;
    //User user1 = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex);
        Anhxa();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

          /*  mDatabase.child("users").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    user1 = dataSnapshot.getValue(User.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
            String dateuser = user1.getDate().toString();
            editText.setText(dateuser); */


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();
                onBackPressed();
            }
        });
    }
    public void change(){
        String edt = editText.getText().toString();
        Boolean gender ;
        if(edt.equals("Nam")) gender = true;
        else gender = false;
        mDatabase.child("users").child(user.getUid()).child("gender").setValue(gender, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null){
                    Toast.makeText(SexActivity.this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SexActivity.this,"Xảy ra lỗi",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Anhxa() {
        back = findViewById(R.id.btn_sex_back);
        save = findViewById(R.id.btn_sex_save);
        editText=findViewById(R.id.edt_sex);
    }
}
