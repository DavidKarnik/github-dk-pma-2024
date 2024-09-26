package com.example.myapp002myownlinearlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etSurname, etPlace, etVek;
    private TextView tvInformation;
    private String[] colors = {"#FF0000", "#00FF00", "#0000FF", "#FFFF00", "#FF00FF", "#F0F0F0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etPlace = findViewById(R.id.etPlace);
        etVek = findViewById(R.id.etVek);
        tvInformation = findViewById(R.id.tvInformation);
        Button btnSend = findViewById(R.id.btnSend);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnChangeColor = findViewById(R.id.btnChangeColor);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String surname = etSurname.getText().toString();
                String place = etPlace.getText().toString();
                String vek = etVek.getText().toString();

                String info = "Jméno: " + name + "\n" +
                        "Příjmení: " + surname + "\n" +
                        "Obec: " + place + "\n" +
                        "Věk: " + vek;

                tvInformation.setText(info);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText("");
                etSurname.setText("");
                etPlace.setText("");
                etVek.setText("");
                tvInformation.setText("");
            }
        });

        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int randomIndex = random.nextInt(colors.length);
                String randomColor = colors[randomIndex];
                tvInformation.setBackgroundColor(Color.parseColor(randomColor));
            }
        });
    }
}
