// MainActivity.java
package com.example.myapp009asharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText, ageEditText;
    private CheckBox adultCheckBox;
    private Button saveButton, loadButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        adultCheckBox = findViewById(R.id.adultCheckBox);
        saveButton = findViewById(R.id.saveButton);
        loadButton = findViewById(R.id.loadButton);

        sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);

        saveButton.setOnClickListener(view -> saveData());
        loadButton.setOnClickListener(view -> loadData());
    }

    private void saveData() {
        String name = nameEditText.getText().toString();
        int age = Integer.parseInt(ageEditText.getText().toString());
        boolean isAdult = adultCheckBox.isChecked();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putInt("age", age);
        editor.putBoolean("isAdult", isAdult);
        editor.apply();

        Toast.makeText(this, "Data uložena", Toast.LENGTH_SHORT).show();
    }

    private void loadData() {
        String name = sharedPreferences.getString("name", "");
        int age = sharedPreferences.getInt("age", 0);
        boolean isAdult = sharedPreferences.getBoolean("isAdult", false);

        nameEditText.setText(name);
        ageEditText.setText(String.valueOf(age));
        adultCheckBox.setChecked(isAdult);

        Toast.makeText(this, "Data načtena", Toast.LENGTH_SHORT).show();
    }
}
