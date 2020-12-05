package com.example.application.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.application.R;
import com.example.application.account.User;
import com.example.application.activity.AccountActivity;
import com.example.application.activity.LoginActivity;
import com.example.application.viewmodel.MenuViewModel;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuFragment extends Fragment {
    private User user1 = new User();
    private DatabaseReference mDatabase;
    LinearLayout linearLayout_header;
    ImageView imageView_header;
    ImageView img_avatar;
    TextView textView_name;
    TextView textView_gmail;
    FirebaseAuth mAuth;
    Button logoutAccount;
    //OnclickLinearListener onclickLinearListener;

    private MenuViewModel mViewModel;
    private View view;

    public static MenuFragment newInstance() {
        MenuFragment f = new MenuFragment();
        return f;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_menu, container, false);
        linearLayout_header = view.findViewById(R.id.linear_menu);
        imageView_header =view.findViewById(R.id.image_menu_header);
        textView_name=view.findViewById(R.id.textview_name);
        textView_gmail=view.findViewById(R.id.textview_gmail);
        img_avatar=view.findViewById(R.id.img_avatar);
        logoutAccount=view.findViewById(R.id.button_logout);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        if(user != null) {
            mDatabase.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    user1 = dataSnapshot.getValue(User.class);

                    String name = user1.getName();
                    textView_name.setText(name);
                    textView_gmail.setText(user1.getGmail());
                    String photoURL = user1.getPhotoUrl().toString();
                    Glide.with(getActivity()).load(photoURL).into(img_avatar);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
        }


        linearLayout_header.setClickable(true);
        linearLayout_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(), AccountActivity.class));
            }
        });
        logoutAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(),"Đăng xuất thành công",Toast.LENGTH_SHORT).show();
            }
        });
        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
    }





}