package com.Hogwart.object;

public class Wand {
    private Core core;
    private int length;

    public Wand(Core core, int length) {
        this.core = core;
        this.length = length;
    }


    public Core getCore() {
        return core;
    }

    public int getLength() {
        return length;
    }
}


