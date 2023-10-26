package com.example.dicesroller;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TextView rollResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rollButton = findViewById(R.id.rollButton);
        rollResult = findViewById(R.id.rollResult);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressButton();
            }
        });
    }

    private void pressButton() {
        rollResult.setText(String.valueOf(rollD6()));
    }

    private int rollD6() {
        int min = 1;
        int max = 6;
        return (int)Math.floor(Math.random() * (max - min + 1) + min);
    }
}