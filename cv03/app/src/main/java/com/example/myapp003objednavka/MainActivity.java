package com.example.myapp003objednavka;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp003objednavka.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.radio1.setOnClickListener(v -> binding.imageView.setImageResource(R.drawable.comp1));
        binding.radio2.setOnClickListener(v -> binding.imageView.setImageResource(R.drawable.comp2));
        binding.radio3.setOnClickListener(v -> binding.imageView.setImageResource(R.drawable.comp3));

        binding.buttonObjednej.setOnClickListener(v -> {
            String procesor = binding.checkbox4.isChecked() ? "\n + lepší procesor" : "";
            String monitor = binding.checkbox5.isChecked() ? "\n + lepší monitor" : "";
            String zaruka = binding.checkbox6.isChecked() ? "\n + dvojitá záruka" : "";

            binding.souhrn.setText("Souhrn objednávky:" + procesor + monitor + zaruka);
        });

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
