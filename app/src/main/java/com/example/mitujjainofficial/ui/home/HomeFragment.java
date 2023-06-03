package com.example.mitujjainofficial.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mitujjainofficial.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private ImageSlider imageSlider;
    private ImageView map;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider = view.findViewById(R.id.slider);

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.image1new, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image2new, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image3new, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image4new, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });


        return view;
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=MIT Group of Institutes , Ujjain");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}



