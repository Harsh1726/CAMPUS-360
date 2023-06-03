package com.example.mitujjainofficial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class ShareAppActivity extends AppCompatActivity {

    private TextView textViewShareAppLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_app);

        getSupportActionBar().setTitle("Share App");

        textViewShareAppLink = findViewById(R.id.textViewShareAppLink);
        textViewShareAppLink.setMovementMethod(LinkMovementMethod.getInstance());

    }
}