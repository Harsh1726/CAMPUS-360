package com.example.mitujjainofficial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CollegeNavigationDrawerWorks extends AppCompatActivity {

    private Uri uri;
    private ImageView websiteRedirect, studentloginRedirect, resultRedirect, feesRedirect, idcardRedirect;
    private Intent intent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_navigation_drawer_works);

        getSupportActionBar().hide();

        websiteRedirect = findViewById(R.id.websiteRedirect);
        studentloginRedirect = findViewById(R.id.studentloginRedirect);
        resultRedirect = findViewById(R.id.resultRedirect);
        feesRedirect = findViewById(R.id.feesRedirect);
        idcardRedirect = findViewById(R.id.idcardRedirect);



        websiteRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uri = Uri.parse("http://www.mitujjain.ac.in/");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        studentloginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uri = Uri.parse("https://www.rgpv.ac.in/Login/StudentLogin.aspx");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        resultRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uri = Uri.parse("http://result.rgpv.ac.in/Result/ProgramSelect.aspx");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        feesRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uri = Uri.parse("https://www.onlinesbi.sbi/sbicollect/icollecthome.htm");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        idcardRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CollegeNavigationDrawerWorks.this, IDCardActivity.class);
                startActivity(intent);

            }
        });
    }
}