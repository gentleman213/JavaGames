package com.Hogwart;
import lombok.*;

@Getter @Setter
@ToString
abstract class AbstractSpell {
    private String name;
    private int level;
    private boolean dark;
    private double successRate;

    public AbstractSpell(String name, int level, boolean dark, double successRate) {
        this.name = name;
        this.level = level;
        this.dark = dark;
        this.successRate = successRate;
    }

    public abstract boolean cast();
    public boolean calculateSuccess() {
        double randomNumber = Math.random(); // génère un nombre aléatoire entre 0 et 1
        return randomNumber <= this.getSuccessRate();
    }
}