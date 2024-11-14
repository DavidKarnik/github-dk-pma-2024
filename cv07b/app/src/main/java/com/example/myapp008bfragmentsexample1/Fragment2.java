package com.example.myapp008bfragmentsexample1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout, container, false);
        View circleView = view.findViewById(R.id.circleView);

        circleView.setOnTouchListener((v, event) -> {
            int color = Color.rgb((int) event.getX() % 255, (int) event.getY() % 255, 150);
            circleView.setBackgroundColor(color);
            return true;
        });

        return view;
    }
}
