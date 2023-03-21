package com.Hogwart;

public class Enemy extends Character{
    int wizardXp;

    public Enemy(String name, int wizardXp){
        super(name,(int)(Math.random()*wizardXp + wizardXp/3+5),(int)(Math.random()*(wizardXp/4+2) + 1));
        //assigning variables
        this.wizardXp = wizardXp;
    }

    @Override
    public int attack(){
        return (int) (Math.random()*(wizardXp/4 + 1) + xp/4 + 3);
    }

    @Override
    public int defend(){
        return (int) (Math.random()*(wizardXp/4 + 1) + xp/4 + 3);
    }
}
