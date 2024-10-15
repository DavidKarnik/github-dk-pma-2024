package com.example.myapp005moreactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp005moreactivities.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;
    private String receivedText = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Second Activity");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        receivedText = getIntent().getStringExtra("text");

        binding.textView.setText(receivedText);
    }

    public void backToMainActivity(View view) {
        Intent intent = new Intent();
        intent.putExtra("text", receivedText);
        setResult(RESULT_OK, intent);
        finish();  // Ukončí aktivitu a vrátí se do MainActivity s daty
    }

    public void openThirdActivity(View view) {
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("text", receivedText);
        startActivity(intent);
    }
}