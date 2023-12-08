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
    Button dicesQuantity;

    Button modificatorButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rollButton = findViewById(R.id.rollButton);
        rollResult = findViewById(R.id.rollResult);
        diceTypeButton = findViewById(R.id.diceTypeButton);
        rollHistory = findViewById(R.id.resultsHistoryLabel);
        dicesQuantity = findViewById(R.id.dicesQuantityButton);
        modificatorButton = findViewById(R.id.modificatorButton);
        rollButton.setOnClickListener(view -> pressRoll());

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
                                diceType = diceType.next(1);
                                diceTypeButton.setText(String.valueOf(diceType));
                                rollResult.setText("");
                            }else {
                                diceType = diceType.prev(1);
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

        dicesQuantity.setOnTouchListener(new View.OnTouchListener() {
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
                                int quantity = Integer.parseInt(String.valueOf(dicesQuantity.getText()))+1;
                                dicesQuantity.setText(String.valueOf(quantity));
                            }else {
                                int quantity = Integer.parseInt(String.valueOf(dicesQuantity.getText()))-1;
                                if (quantity>0) dicesQuantity.setText(String.valueOf(quantity));
                                else dicesQuantity.setText(String.valueOf(1));
                            }
                        }
                        mIsDown = false;
                        break;
                }
                return true;
            }
        });

        modificatorButton.setOnTouchListener(new View.OnTouchListener() {
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
                                int quantity = Integer.parseInt(String.valueOf(modificatorButton.getText()))+1;
                                modificatorButton.setText(String.valueOf(quantity));
                            }else {
                                int quantity = Integer.parseInt(String.valueOf(modificatorButton.getText()))-1;
                                modificatorButton.setText(String.valueOf(quantity));
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
        Roll roll = new Roll(diceType, Integer.parseInt(String.valueOf(dicesQuantity.getText())), Integer.parseInt(String.valueOf(modificatorButton.getText())));
        rollResult.setText(String.valueOf(roll.rollDice()));
        String description = rollHistory.getText()+"\n"+roll.getDescription();
        rollHistory.setText(description);
    }
}