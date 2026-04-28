package com.pluralsight;

public enum Colors {

    TRON(125, 253, 254),
    GREEN(76, 175, 80),
    ORANGE(255, 87, 34),
    AMBER(255,191, 0),
    CRIMSON(220, 20, 60),
    CHAMPAGNE_SILVER(210, 200, 190);


    private final int R;
    private final int G;
    private final int B;

    Colors(int R, int G, int B) {
        this.R = R;
        this.G = G;
        this.B = B;
    }


    public String printWithColor(String text) {
        return "\u001B[38;2;" + R + ";" + G + ";" + B + "m" + text + "\u001b[0m";
    }
}