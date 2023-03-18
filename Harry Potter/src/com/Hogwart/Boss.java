package com.Hogwart;





public class Boss extends AbstractEnemy {

    public Wand wand;
    //Constructor for boss
    public Boss(String name, int maxHp, int strength,Wand wand) {
        super(name, maxHp, strength);
        this.wand = wand;
    }

    //methods
    public int attack() {
        return (int) (Math.random()*(10/4 + 1) + 10/4 + 3);
        //return this.strength * 2*Math.random();
    }

    public void equip(Wand wand){
        this.wand = wand;
    }

    public int defend() {
        return this.strength*2;
    }
}


/*

public class Boss extends Character{
    int bossLevel;
    public Wand wand;
    public Boss(String name, int bossLevel,Wand wand){
        super(name,(int)(Math.random()*10 + 10/3+5));
        //assigning variables
        this.wand = wand;
        this.bossLevel = bossLevel;
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
*/
