package com.example.mitujjainofficial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class IDCardActivity extends AppCompatActivity {

    Button redirectuploadpic ;
    private ProgressBar progressBar;
    private ImageView imageView;
    private FirebaseAuth authProfile;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idcard);

        getSupportActionBar().hide();
        swipeToRefresh();

        imageView = findViewById(R.id.imageView_idcard_dp);
        redirectuploadpic = findViewById(R.id.redirect_to_upload_pic_button);
        progressBar = findViewById(R.id.progressBar);

        redirectuploadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IDCardActivity.this, UploadIdCardPicActivity.class);
                startActivity(intent);
            }
        });

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();


        if (firebaseUser == null) {
            Toast.makeText(IDCardActivity.this, "Something went wrong! ID Card image not available at the moment",
                    Toast.LENGTH_LONG).show();
        } else {

            progressBar.setVisibility(View.VISIBLE);
            showUserProfile(firebaseUser);
        }

    }



    private void swipeToRefresh() {
            // Look up for the swipe Container
            swipeContainer = findViewById(R.id.swipeContainer);

            // Setup Refresh Listener which triggers new data loading
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    // Code to refresh goes here. Make sure to call swipeContainer.setRefreshing(false) once the refresh is complete.
                    startActivity(getIntent());
                    finish();
                    overridePendingTransition(0,0);
                    swipeContainer.setRefreshing(false);

                }
            });

            // Configure refresh colors
            swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

    }


    private void showUserProfile(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();

        // Extracting User Reference from Database for "Registration Of Users"
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registration Of Users");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if(readUserDetails  != null) {


                    // Set ID Card DP (After user has uploaded)
                    Uri uri = firebaseUser.getPhotoUrl();

                    //  ImageViewer setImagerURI() should not be used with regular URIs. So we are using Picasso
                    Picasso.get().load(uri).into(imageView);

                } else {
                    Toast.makeText(IDCardActivity.this, "Something went wrong !", Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(IDCardActivity.this, "Something went wrong !", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }



}