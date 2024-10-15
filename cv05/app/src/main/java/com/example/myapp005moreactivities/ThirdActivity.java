package com.example.myapp005moreactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp005moreactivities.databinding.ActivityThirdBinding;

public class ThirdActivity extends AppCompatActivity {


    private ActivityThirdBinding binding;
    private String receivedText = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        binding = ActivityThirdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Third Activity");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        receivedText = getIntent().getStringExtra("text");

        binding.textView.setText(receivedText);
    }

    public void backToSecondActivity(View view) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}