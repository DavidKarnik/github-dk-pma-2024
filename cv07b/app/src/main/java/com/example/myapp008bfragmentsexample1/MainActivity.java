package com.example.myapp008bfragmentsexample1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.showFormFragmentButton).setOnClickListener(v ->
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new Fragment1())
                        .commit()
        );

        findViewById(R.id.showCircleFragmentButton).setOnClickListener(v ->
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new Fragment2())
                        .commit()
        );
    }
}

