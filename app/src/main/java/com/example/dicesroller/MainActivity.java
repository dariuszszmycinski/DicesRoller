package com.example.dicesroller;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TextView rollResult;
    Dice diceType = Dice.D6;
    Button diceTypeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rollButton = findViewById(R.id.rollButton);
        rollResult = findViewById(R.id.rollResult);
        diceTypeButton = findViewById(R.id.diceTypeButton);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressRoll();
            }
        });

        diceTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressDiceType();
            }
        });
    }

    private void pressRoll() {
        rollResult.setText(String.valueOf(rollDice()));
    }

    private void pressDiceType() {
        diceType = diceType.next();
        diceTypeButton.setText(String.valueOf(diceType));
    }

    private int rollDice() {
        int min = 1;
        int max = diceType.getSides();
        return (int)Math.floor(Math.random() * (max - min + 1) + min);
    }
}