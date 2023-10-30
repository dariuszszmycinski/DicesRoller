package com.example.dicesroller;

public enum Dice {

    D4(4),
    D6(6),
    D8(8),
    D10(10),
    D12(12),
    D20(20),
    D100(100);

    private int sides;

    private static final Dice[] vals = values();

    Dice(int sides) {
        this.sides = sides;
    }

    public int getSides() {
        return sides;
    }

    public Dice next(int range) {
        return vals[(this.ordinal() + range) % vals.length];
    }

    public Dice prev(int range) {
        return vals[(this.ordinal() + vals.length-range) % vals.length];
    }
}
