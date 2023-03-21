package com.Hogwart.character;


public abstract class AbstractEnemy extends Character {
    //Variables
    protected int strength;

    //Constructor for enemy
    public AbstractEnemy(String name, int maxHp, int strength) {
        super(name, maxHp);
        this.strength = strength;
    }

    //methods
    public abstract int attack();
    public abstract int defend();
}