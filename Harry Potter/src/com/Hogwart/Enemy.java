package com.Hogwart;

public class Enemy extends Character{
    int ennemyLevel;

    public Enemy(String name, int ennemyLevel){
        super(name,(int)(Math.random()*10 + 10/3+5));
        //assigning variables
        this.ennemyLevel = ennemyLevel;
    }

    @Override
    public int attack(){
        return (int) (Math.random()*(10/4 + 1) + 10/4 + 3);
    }

    @Override
    public int defend(){
        return (int) (Math.random()*(10/4 + 1) + 10/4 + 3);
    }
}
