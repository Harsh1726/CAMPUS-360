package com.example.mitujjainofficial.ui.about;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mitujjainofficial.R;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class AboutFragment extends Fragment {

    private Uri uri;
    private ImageView gmail, bundgmail, nikkigmail, syedgmail;
    private CircleImageView linkedin, bundlink, nikkilink, syedlink;
    private CircleImageView github, bundgit, nikkigit, syedgit;
    private Intent intent;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);


        gmail = view.findViewById(R.id.gmail);
        linkedin = view.findViewById(R.id.linkedin);
        github = view.findViewById(R.id.github);
        bundgmail = view.findViewById(R.id.bundgmail);
        bundlink = view.findViewById(R.id.bundlink);
        bundgit = view.findViewById(R.id.bundgith);
        nikkigmail = view.findViewById(R.id.nikkigmail);
        nikkilink = view.findViewById(R.id.nikkilink);
        nikkigit = view.findViewById(R.id.nikkigith);
        syedgmail = view.findViewById(R.id.dallagmail);
        syedlink = view.findViewById(R.id.dallalink);
        syedgit = view.findViewById(R.id.dallagith);

        //buttons to visit the links
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse(getString(R.string.gmaillink));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse(getString(R.string.linkedinlink));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse(getString(R.string.githublink));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        bundgmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("mailto:namdevdhruv147@gmail.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        bundlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("https://www.linkedin.com/in/dhruv-namdev-209990209");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        bundgit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("https://github.com/Harsh1726");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        nikkigmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("mailto:ashishdubey15042003@gmail.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        nikkilink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("https://www.linkedin.com/in/ashishdubey2274");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        nikkigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("https://github.com/ashishdubey04");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        syedgmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse("mailto:ajayanjana7470459010@gmail.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        syedlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse(getString(R.string.linkedinlink));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        syedgit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = Uri.parse(getString(R.string.githublink));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        return view;
    }
}
