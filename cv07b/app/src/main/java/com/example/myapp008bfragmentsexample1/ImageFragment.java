package com.example.myapp008bfragmentsexample1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class ImageFragment extends Fragment {
    private int imageResId;

    public static ImageFragment newInstance(int imageResId) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt("image_res", imageResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);

        if (getArguments() != null) {
            imageResId = getArguments().getInt("image_res");
            imageView.setImageResource(imageResId);
        }
        return view;
    }
}
