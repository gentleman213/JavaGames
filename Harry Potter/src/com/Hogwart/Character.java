package com.Hogwart;

public abstract class Character {
    //Variables
    public String name;
    public int maxHp, hp, xp;
    //Constructor for character
    public Character(String name, int maxHp, int xp){
        this.name = name;
        this.maxHp = maxHp;
        this.xp = xp;
        this.hp = maxHp;
    }

    //methods
    public abstract int attack();
    public abstract int defend();

}
