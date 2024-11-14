package com.example.myapp008bfragmentsexample1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class Fragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);

        EditText inputField = view.findViewById(R.id.inputField);
        Button submitButton = view.findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> {
            String message = inputField.getText().toString();
            Snackbar.make(view, "Zadaný text: " + message, Snackbar.LENGTH_LONG).show();
        });

        return view;
    }
}
