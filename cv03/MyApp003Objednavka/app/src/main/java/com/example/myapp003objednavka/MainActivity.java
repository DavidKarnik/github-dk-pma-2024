package com.example.myapp003objednavka;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RadioButton button1 = findViewById(R.id.radio1);
        RadioButton button2 = findViewById(R.id.radio2);
        RadioButton button3 = findViewById(R.id.radio3);

        ImageView obrazek = findViewById(R.id.imageView);

        button1.setOnClickListener(v -> {
            obrazek.setImageResource(R.drawable.comp1);
        });

        button2.setOnClickListener(v -> {
            obrazek.setImageResource(R.drawable.comp2);
        });

        button3.setOnClickListener(v -> {
            obrazek.setImageResource(R.drawable.comp3);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}