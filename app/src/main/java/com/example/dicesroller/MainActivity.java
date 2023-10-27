package com.example.dicesroller;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TextView rollResult;
    private TextView rollHistory;
    Dice diceType = Dice.D6;
    Button diceTypeButton;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rollButton = findViewById(R.id.rollButton);
        rollResult = findViewById(R.id.rollResult);
        diceTypeButton = findViewById(R.id.diceTypeButton);
        rollHistory = findViewById(R.id.resultsHistoryLabel);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressRoll();
            }
        });

        diceTypeButton.setOnTouchListener(new View.OnTouchListener() {
            private float mPreviousY;
            boolean mIsDown;
            @Override
            public boolean onTouch(View view, MotionEvent e) {
                float y ;
                float dy = 0;

                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mIsDown = true;
                        mPreviousY = e.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mIsDown){
                            y = e.getY();
                            dy = y - mPreviousY;
                        }
                    case MotionEvent.ACTION_UP:
                        if (mIsDown){
                            if (dy<=0){
                                diceType = diceType.next();
                                diceTypeButton.setText(String.valueOf(diceType));
                                rollResult.setText("");
                            }else {
                                diceType = diceType.prev();
                                diceTypeButton.setText(String.valueOf(diceType));
                                rollResult.setText("");
                            }
                        }
                        mIsDown = false;
                        break;
                }
                return true;
            }
        });
    }

    private void pressRoll() {
        rollResult.setText(String.valueOf(rollDice()));
    }

    private int rollDice() {
        int min = 1;
        int max = diceType.getSides();
        int result = (int)Math.floor(Math.random() * (max - min + 1) + min);
        String text = rollHistory.getText()+String.valueOf(result)+", ";
        rollHistory.setText(text);
        return result;
    }
}