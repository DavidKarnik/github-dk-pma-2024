package com.example.myapp008afragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonFragment1 = findViewById(R.id.button_fragment1);
        Button buttonFragment2 = findViewById(R.id.button_fragment2);

        buttonFragment1.setOnClickListener(view -> loadFragment(new Fragment1()));
        buttonFragment2.setOnClickListener(view -> loadFragment(new Fragment2()));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
