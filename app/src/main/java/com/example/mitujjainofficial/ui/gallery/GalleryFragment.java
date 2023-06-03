package com.example.mitujjainofficial.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mitujjainofficial.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {

    RecyclerView cullitRecycler, codingRecycler, sportsRecycler, councilRecycler, otherRecycler;
    GalleryAdapter adapter;

    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gallery, container, false);

        cullitRecycler = view.findViewById(R.id.cullitRecycler);
        codingRecycler = view.findViewById(R.id.codingRecycler);
        sportsRecycler = view.findViewById(R.id.sportsRecycler);
        councilRecycler = view.findViewById(R.id.councilRecycler);
        otherRecycler = view.findViewById(R.id.otherRecycler);

        reference = FirebaseDatabase.getInstance().getReference().child("gallery");

        getcullitImage();

        getcodingImage();

        getsportsImage();

        getcouncilImage();

        getotherImage();

        return view;

    }

    private void getotherImage() {

        reference.child("Other Events").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    String data = (String) snapshot.getValue();
                    imageList.add(data);

                }

                adapter = new GalleryAdapter(getContext(), imageList);

                otherRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                otherRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getcouncilImage() {

        reference.child("Council Meetings").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    String data = (String) snapshot.getValue();
                    imageList.add(data);

                }

                adapter = new GalleryAdapter(getContext(), imageList);

                councilRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                councilRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getsportsImage() {

        reference.child("Sports").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    String data = (String) snapshot.getValue();
                    imageList.add(data);

                }

                adapter = new GalleryAdapter(getContext(), imageList);

                sportsRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                sportsRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void getcodingImage() {

        reference.child("Coding Events").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    String data = (String) snapshot.getValue();
                    imageList.add(data);

                }

                adapter = new GalleryAdapter(getContext(), imageList);

                codingRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                codingRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getcullitImage() {

        reference.child("Culturals and Literary Events").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    String data = (String) snapshot.getValue();
                    imageList.add(data);

                }

                adapter = new GalleryAdapter(getContext(), imageList);

                cullitRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                cullitRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}