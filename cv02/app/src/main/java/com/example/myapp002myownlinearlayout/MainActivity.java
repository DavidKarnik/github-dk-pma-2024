package com.example.myapp002myownlinearlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private SeekBar seekBarSize;
    private String[] colors = {"#FF0000", "#00FF00", "#0000FF", "#FFFF00", "#FF00FF"};
    private String[] texts = {"Hello World!", "Ahoj světe!", "Vítejte!", "PMA Aplikace", "Android 1234"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);
        seekBarSize = findViewById(R.id.seekBarSize);
        Button btnChangeText = findViewById(R.id.btnChangeText);
        Button btnChangeColor = findViewById(R.id.btnChangeColor);

        seekBarSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // velikosti textu dle posuvníku
                tvDisplay.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // změna textu
        btnChangeText.setOnClickListener(v -> {
            Random random = new Random();
            int randomIndex = random.nextInt(texts.length);
            tvDisplay.setText(texts[randomIndex]);
        });

        // změna barvy textu
        btnChangeColor.setOnClickListener(v -> {
            Random random = new Random();
            int randomIndex = random.nextInt(colors.length);
            String randomColor = colors[randomIndex];
            tvDisplay.setTextColor(Color.parseColor(randomColor));
        });
    }
}
