package com.example.dicesroller;

public class Roll {
    private final Dice DICE;
    private final int QUANTITY;
    private final int MODIFICATOR;

    private String description;

    public String getDescription() {
        return description;
    }

    public Roll(Dice dice, int quantity, int modificator) {
        this.DICE = dice;
        this.QUANTITY = quantity;
        this.MODIFICATOR = modificator;
    }

    public int rollDice() {
        int min = 1;
        int max = DICE.getSides();
        int resultSum = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(QUANTITY).append(DICE);
        if (MODIFICATOR>0) sb.append("+").append(MODIFICATOR);
        if (MODIFICATOR<0) sb.append(MODIFICATOR);
        sb.append(": ");
        for (int i = 0; i < QUANTITY; i++) {
            int result = (int) (Math.floor(Math.random() * (max - min + 1) + min));
            resultSum += result;
            sb.append(result).append(", ");
        }
        resultSum += MODIFICATOR;
        sb.append("total = ").append(resultSum);
        description = sb.toString();
        return resultSum;
    }
}
