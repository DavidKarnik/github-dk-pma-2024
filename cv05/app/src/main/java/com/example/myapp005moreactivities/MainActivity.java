package com.example.myapp005moreactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp005moreactivities.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Main Activity");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    public void openSecondActivity(View view) {
        String text = binding.textToSecond.getText().toString();
        binding.textView.setText(text);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("text", text);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            String returnedText = data.getStringExtra("text");
            binding.textView.setText(returnedText);
        }
    }

}
