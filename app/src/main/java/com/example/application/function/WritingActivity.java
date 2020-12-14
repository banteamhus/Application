package com.example.application.function;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.application.R;
import com.example.application.account.User;
import com.example.application.object.Comment;
import com.example.application.object.Like;
import com.example.application.postmanage.Post;
import com.example.application.postmanage.PostContent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hendraanggrian.appcompat.widget.SocialTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WritingActivity extends AppCompatActivity {

    PostContent postContent=new PostContent("1","Some people believe that there are no compelling reasons for us to protect animal species from extinction as it occurs naturally. I personally disagree with this conviction and will support my argument in the essay below.\n" +
            "\n" +
            "It is true that millions of years ago, many ancient species of animals, such as dinosaurs, were wiped out due to a gradual shift in climate and changing sea levels, according to some hypotheses. However, these environmental factors are not the primary contributor to the disappearance of certain species nowadays. Industrial activities have been devastating the natural habitats of wildlife and disturbing the food chain, causing the mass extinction of countless species. The increased demand for goods made from animals’ products, such as skins and horns, also leads to the rampant poaching of wild, endangered animals, rhinos for instance. In this regard, humans are held accountable and should do what is needed to rectify the situation.\n" +
            "\n" +
            "Other justifications for saving wild animals involve the significant roles that they play in not only the balance of the ecosystem but also our lives. Everything in nature is connected, and if one species becomes extinct, many other animals and even plants will suffer as the food chain is disrupted. Wild animals also have great aesthetic and socio-cultural values. They contribute to our rich bio-diversity that makes this planet a beautiful place. In numerous places around the world, many types of animals play an important role in different cultures. For example, in some religions, cows are revered and worshiped as gods.\n" +
            "\n" +

            "The disappearance of many animal species does not always occur as a natural process but as a conse quence of our doings. It is our obligation to help preserve wild animals because their extinction will have a severe influence on many important aspects of our lives.");;

    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();;

    private DatabaseReference mDatabase;
    public ImageView imageProfile;
    public ImageView postImage;
    public ImageView like;
    public ImageView comment;
    public ImageView save;
    //public ImageView more;
    String id,id_user;
    public TextView username ;
    public TextView noOfLikes;
    public TextView author;
    public TextView noOfComments;
    SocialTextView description;

    List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        posts =new ArrayList<>();

        imageProfile =findViewById(R.id.image_profile);
        postImage = findViewById(R.id.post_image);
        like = findViewById(R.id.like);
        comment = findViewById(R.id.comment);
        save = findViewById(R.id.save);
        //more = findViewById(R.id.more);

        username = findViewById(R.id.username);
        noOfLikes = findViewById(R.id.no_of_likes);
        author = findViewById(R.id.author);
        noOfComments = findViewById(R.id.no_of_comments);
        description = findViewById(R.id.description);
        //
        String userName = "No Author";
        String Title="";
        String image="";
        String photo="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAMFBMVEXFxcX////CwsLGxsb7+/vT09PJycn19fXq6urb29ve3t7w8PDOzs7n5+f5+fnt7e30nlkBAAAFHUlEQVR4nO2dC5qqMAyFMTwUBdz/bq+VYYrKKJCkOfXmXwHna5uTpA+KwnEcx3Ecx3Ecx3Ecx3Ecx3Ecx3Ecx3Ecx3EcA2iO9cdIc5PUdO257y+BU39u66b4HplE3fk6VIcnqmNfl1+gksr6+iIucjl3WYukor7+re6Hoe1y1UhNO3zUd+fUFRmKpOa0Tt6dY5ubRCrOG/QFLk1WGmnt/JxzykcjdZ/jyxJDLlOV2l36AtcsJJb9boG3YcR3DuqODIE3ztYKPkDdmwRmpUToUaSaq++AvRgZMWbOpbQW8hdCAm8ZDugoikzREdCJ2okJPBx6azFLNOwoOgcxojJ98JkaTSJxMpklKrCAKhZGI0drTY/wU5lXoJYibannV9NYy4oozNEAkPHTjop+DTDxVGkIgYJNoyQQJtiIW+EMjGAjm649AjGIaqswcEFQKJ2QPlJbqytki6ZXAAZRJ52J2McaUowzAfs+uFzrYhnzaapphiPWdaJWShqxjqa6kTTQ205TVbsfMa6htL0iYOsXpJrQjHSmCkv1QGPtiHqlYcQ21Gj7fcDU8xOEUuNgSltPzexh+HqFlanCBHZ4OLhCV+gK/3OF6vWvucLv98MUOY2pwu/PS/+D2qJU7pYGbOvDFDW+bbON9p3o3oRxn0bfLgZTgSn6pSfrtr56qLHemtHPTK2319SzGvtjQ9qeb39WgS66Cm073nd0U1PzDdJCO3Gzn6TKpl9Zq7ujGWsQhlA3NwWIMwG9zM08Y/tBrR9VWeczv5CSQuuUNKIUTk23ZJ5RKfVhjnkXotfWIlgX2BSCDYbZR+QTcLhb3dKZDUY2M0d4KWItwhHRah/zsrOgKw4wycwjcgEVcgQDQo23CqSiWEJkFAfod2oE1uIFdA1OsCPqFXYNTjCfb8Ez+iX2x5sKLlVbhtqdDcar9ZevhnbZxoBUD35k23t0d304LYs1ELVbnfFaZ/REJJX9niP8Q19moZGo3m8XR/yBvOnjFfsXcI2c8ZuNo7WMP5HQh6yRGrlmFOJTnyTcT+zRlqPUBI2gTVWNUzUna1ERgecgF4GpNBQ38jGqxVLzQA1A31Rrhk6Yz9QEh/WND0GnuG9huhiTXJkxfAizTHLr6cbJKN6UCU6x/2DTRE1xEeEXi3O0ZUqBN4nJRzHhFB1JPlFTBZlI2kQ8zc3KJ1Le8DIRmFJiknuVS6RK4Ej/JtBfJErDSzOBiY4wJHX6Z1I4v1GUmdCPNirnLLeg3oJLcbX5PcpHNbRvOa1A956QmRPOUXVF+zkaUJynpkYR0bOMJH2nNej1pqyV/aKkz9jr5yj5vrXXz1F5SQLACiMapmierj2ikLyleKdlA/I/2oFxiglxx9B+mHwz0lf34IZQfhDRhlD6bhvgEAoPYooHkTczSIZTLC+cEExsoNKZiGBiY9cCfo/Y/SjIOBMQizWWTe73CMUasJx7jlD+DdKdWUKoY4PRYFtGpO0G1Lx4RaadgTtJhf4fiGqGIwKWCGuGIwKWqP+7IxYCzygjR9IAO5pC7Da9g70TBVpWRNgFBlgT8RV2WxHbKwJMv4BOaEaYaU2K16yZMN/qgV+G7IWIvwyZCxHeDQMsR8wg0DBDDXB5H2EV+hkEGmaoySHQsEJNFoGGFWrAq98JRhUMX1iMMMqLLEIpK5jCbd4vw9nSt/72lewXiN6jmdjfq8Hdknlk92ZwJnbIMMRM7JBhiFlUFoHd1UWaP1QKsPsHA5mkNB+Smn9JqV3wskatnQAAAABJRU5ErkJggg==";
        //
        Intent intent = getIntent();
        id = intent.getExtras().getString("Id");
        String postcontent_id = intent.getExtras().getString("Content");
        if(intent.getExtras().getString("Photo") != null){
            photo = intent.getExtras().getString("Photo");
        }
        if(intent.getExtras().getString("Id_user") != null){
            id_user = intent.getExtras().getString("Id_user");
        }
        if(intent.getExtras().getString("Image") != null) {
            image = intent.getExtras().getString("Image");
        }
        if( intent.getExtras().getString("Name") != null ) {
            userName = intent.getExtras().getString("Name");
        }
        if(intent.getExtras().getString("Title") != null ) {
            Title = intent.getExtras().getString("Title");
        }

        String Description = "";
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("PostsContent");
        // Toast.makeText(this,Id_user, Toast.LENGTH_SHORT).show();
        mDatabase.child(postcontent_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PostContent a = dataSnapshot.getValue(PostContent.class);
                description.setText(a.getContent());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        //  String Description = postContent.getContent();
        //
        username.setText(userName);
        author.setText(userName);
        //   description.setText(Description);
        Glide.with(this).load(photo).into(imageProfile);
        Glide.with(this).load(image).into(postImage);

        //noOfComments lấy comment database equals id;
        isSaved(id, save);
        noOfLikes(id, noOfLikes);
        getComments(id, noOfComments);
        isLiked(id, like);
        //
        String finalTitle = Title;
        String finalId_user = id_user;
        //comment
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CommentActivity.class);
                intent.putExtra("postId", finalTitle);
                intent.putExtra("authorId", finalId_user);
                startActivity(intent);
            }
        });
        //like
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //like();
                if (like.getTag().equals("like")) {
                    FirebaseDatabase.getInstance().getReference().child("Likes")
                            .child(id).child(firebaseUser.getUid()).setValue(true);


                } else {
                    FirebaseDatabase.getInstance().getReference().child("Likes")
                            .child(id).child(firebaseUser.getUid()).removeValue();
                }
            }
        });
        //save
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (save.getTag().equals("save")) {
                    FirebaseDatabase.getInstance().getReference().child("Saves")
                            .child(firebaseUser.getUid()).child(id).setValue(true);

                } else {
                    FirebaseDatabase.getInstance().getReference().child("Saves")
                            .child(firebaseUser.getUid()).child(id).removeValue();
                }
            }
        });
    }

    /*
    private void like(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Likes").child(id);
        Like a = new Like(id,id_user);

        ref.child(id_user).setValue(a).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(WritingActivity.this, "Like added!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(WritingActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    */
    // set like
    private void isLiked(String postId, final ImageView imageView) {

        FirebaseDatabase.getInstance().getReference().child("Likes").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(firebaseUser.getUid()).exists()) {
                    imageView.setImageResource(R.drawable.ic_liked);
                    imageView.setTag("liked");
                } else {
                    imageView.setImageResource(R.drawable.ic_like);
                    imageView.setTag("like");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    // set account_like
    private void noOfLikes (String postId, final TextView text) {
        FirebaseDatabase.getInstance().getReference().child("Likes").child(postId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                text.setText(dataSnapshot.getChildrenCount() + " lượt thích");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //
    private void getComments (String postId, final TextView text) {
        FirebaseDatabase.getInstance().getReference().child("Comments").child(postId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                text.setText("Xem tất cả " + dataSnapshot.getChildrenCount() + " bình luận");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    // set saved
    private void isSaved (final String postId, final ImageView image) {
        FirebaseDatabase.getInstance().getReference().child("Saves").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(postId).exists()) {
                    image.setImageResource(R.drawable.ic_save_black);
                    image.setTag("saved");
                } else {
                    image.setImageResource(R.drawable.ic_save);
                    image.setTag("save");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}