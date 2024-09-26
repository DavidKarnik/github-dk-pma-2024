package com.example.myapp01linearlayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etSurname, etPlace, etVek;
    private TextView tvInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Najdeme jednotlivé prvky layoutu
        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etPlace = findViewById(R.id.etPlace);
        etVek = findViewById(R.id.etVek);
        tvInformation = findViewById(R.id.tvInformation);
        Button btnSend = findViewById(R.id.btnSend);
        Button btnDelete = findViewById(R.id.btnDelete);

        // Logika pro tlačítko Odeslat
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

        // Logika pro tlačítko Vymazat
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
    }
}
