package org.example.character;

public abstract class Character {
    //Variables
    public String name;
    public int maxHp, hp;
    //Constructor for character
    public Character(String name, int maxHp){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    //methods
    public abstract int attack();
    public abstract int defend();


}
