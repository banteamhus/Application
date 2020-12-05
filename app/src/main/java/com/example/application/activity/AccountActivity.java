package com.example.application.activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.application.R;
import com.example.application.account.User;
import com.example.application.update_account.DateActivity;
import com.example.application.update_account.NameActivity;
import com.example.application.update_account.PasswordActivity;
import com.example.application.update_account.SexActivity;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class AccountActivity extends AppCompatActivity {
    private static final  int GALLERY_IMAGE_CODE = 100 ;
    private static final  int CAMERA_IMAGE_CODE = 200 ;
    Uri image_uri = null ;
   // Button btn1;
  //  Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    TextView userid,usergmail,username,usergender,userdate,role;
    ImageView avatar;
    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private User user1 = new User();
    Button deleteAccount;
    Button logoutAccount;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Anhxa();

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        if(user != null) {
            mDatabase.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    user1 = dataSnapshot.getValue(User.class);
                    if(user1.getPhotoUrl() != null) {String photoURL = user1.getPhotoUrl();
                        Glide.with(AccountActivity.this).load(photoURL).into(avatar);}
                    String name = user1.getName();
                    String gmail = user.getEmail();
                    String date = user1.getDate();
                    boolean gender = user1.getGender();

                    userid.setText(user.getUid());
                    int a = user1.getRole();
                    if(a==0) {
                        role.setText("a");
                    }
                    else if(a==1){
                        role.setText("Tài khoản Premium");
                    }
                    else{
                        role.setText("Tài khoản Admin");
                    }
                    username.setText(name);
                    usergmail.setText(gmail);
                    userdate.setText(date);
                    if(gender == false) usergender.setText("Nữ");
                    else usergender.setText("Nam");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
            id = user.getUid();


        }
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                }
                            }
                        });


                LoginManager.getInstance().logOut();
                mDatabase.child(id).removeValue();
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(AccountActivity.this,"Xóa tài khoản thành công",Toast.LENGTH_SHORT).show();
            }
        });
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permission();
                imagePickDialog();

            }
        });

       /* btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, IDActivity.class);
                startActivity(intent);
            }
        });
        */
       /* btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, UserActivity.class);
                startActivity(intent);
            }
        }); */

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
      //  btn1=findViewById(R.id.btn_account_id);
      //  btn2=findViewById(R.id.btn_account_user);
        btn3=findViewById(R.id.btn_account_pw);
        btn4=findViewById(R.id.btn_account_name);
        btn5=findViewById(R.id.btn_account_sex);
        btn6=findViewById(R.id.btn_account_date);
        userid =findViewById(R.id.textview_userid);
        usergmail=findViewById(R.id.textview_tendangnhap);
        username=findViewById(R.id.textview_tentaikhoan);
        avatar=findViewById(R.id.img_avatar);
        usergender =findViewById(R.id.textview_gioitinh);
        userdate = findViewById(R.id.textview_ngaysinh);
        deleteAccount=findViewById(R.id.button_deleteAccount);
        role = findViewById(R.id.text_role);
    }
    private void imagePickDialog() {
        //here 0 is for camera and 1 is for gallery so please do it like me
        String[] options = {"Camera" , "Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose image from");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    cameraPick();
                }
                if (which == 1){
                    galleryPick();

                }
            }
        });
        builder.create().show();

    }
    private void changeAvatar(){
        //getImage from Image view ;
        Bitmap bitmap = ((BitmapDrawable)avatar.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG , 100 , baos);
        byte[] data = baos.toByteArray();
        FirebaseUser user = mAuth.getCurrentUser();
        // now we will creat the referense of storage in firebase as we have al ready added the libraries
        StorageReference reference = FirebaseStorage.getInstance().getReference().child("user_photo").child(user.getUid());
        reference.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                String downloadUri = uriTask.getResult().toString();
                if (uriTask.isSuccessful()){

                    mDatabase.child(user.getUid()).child("photoUrl").setValue(downloadUri, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            if(error == null){
                                //Toast.makeText(AccountActivity.this,"Cập nhật ảnh đại diện thành công",Toast.LENGTH_SHORT).show();
                            }
                            else{
                              //  Toast.makeText(AccountActivity.this,"Xảy ra lỗi",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }



    private void galleryPick() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        //this is for all tape of images make sure you didnot make speeling msitake
        intent.setType("image/*");
        startActivityForResult(intent , GALLERY_IMAGE_CODE);
    }

    private void cameraPick() {
        //here we will do this for camera
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE , "Temp Pick");
        contentValues.put(MediaStore.Images.Media.TITLE , "Temp desc");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI , contentValues);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT , image_uri);
        startActivityForResult(intent , CAMERA_IMAGE_CODE);
    }
    private void permission(){

        Dexter.withContext(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken toke) {
                        toke.continuePermissionRequest();
                    }
                }).check();
        //hold alt key and press enter to import the library
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {

            }
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == GALLERY_IMAGE_CODE){
                image_uri = data.getData();
                avatar.setImageURI(image_uri);
            }
            if (requestCode == CAMERA_IMAGE_CODE){
                avatar.setImageURI(image_uri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}