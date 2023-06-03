package com.example.mitujjainofficial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CurrentUserActivity extends AppCompatActivity {

    private TextView username,email;
    Button Homebutton;

    FirebaseAuth authProfile = FirebaseAuth.getInstance();
    FirebaseUser user = authProfile.getCurrentUser();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_user);

        username = findViewById(R.id.currentUserNameProfile);
        email = findViewById(R.id.CurrentEmail);

        if(user != null) {
            username.setText(user.getDisplayName());
            email.setText(user.getEmail());


        }


        Homebutton = findViewById(R.id.Homebutton);
        Homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CurrentUserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(user == null) {
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
    }
}