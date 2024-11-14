package com.example.myapp007toastsnackbar;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toastButton = findViewById(R.id.button_toast);
        Button snackbarButton = findViewById(R.id.button_snackbar);

        // Custom Toast
        // ikona je dle Manifest -> android:roundIcon
        toastButton.setOnClickListener(view -> {
            Toast toast = Toast.makeText(MainActivity.this, "Zpráva Toast -> Custom icon", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        });

        // Snackbar na tlačítko
        snackbarButton.setOnClickListener(view -> {
            Snackbar.make(view, "Zpráva Snackbar -> OK pro potvrzení", Snackbar.LENGTH_SHORT)
                    .setAction("OK", v -> Toast.makeText(MainActivity.this, "Akce potvrzena", Toast.LENGTH_SHORT).show())
                    .show();
        });
    }
}
