package com.example.mitujjainofficial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class FeedbackActivity extends AppCompatActivity {

    EditText memailAddress, msubject, mmessageFeedback;
    Button msendbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        memailAddress = findViewById(R.id.emailAddress);
        msubject = findViewById(R.id.subject);
        mmessageFeedback =findViewById(R.id.messageFeedback);
        msendbtn = findViewById(R.id.sendbtn);

        msendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String recipient = memailAddress.getText().toString().trim();
                String subjective = msubject.getText().toString().trim();
                String messaging = mmessageFeedback.getText().toString().trim();

                sendEmail(recipient, subjective, messaging);
            }
        });
    }

    private void sendEmail(String recipient, String subjective, String messaging) {

        Intent memailIntent = new Intent(Intent.ACTION_SEND);

        memailIntent.setData(Uri.parse("mailto:"));
        memailIntent.setType("text/plain");

        memailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {recipient});
        memailIntent.putExtra(Intent.EXTRA_SUBJECT, subjective);
        memailIntent.putExtra(Intent.EXTRA_TEXT, messaging);

        try {
            startActivity(Intent.createChooser(memailIntent, "Choose an Email App"));

        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}