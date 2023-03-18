package com.Hogwart;

//public class Enemy extends Character{
//    int enemyLevel;
//
//    public Enemy(String name, int enemyLevel){
//        //super(name,(int)(Math.random()*10 + 10/3+5));
//        //assigning variables
//        super(name,enemyLevel);
//        this.enemyLevel = enemyLevel;
//
//    }
//
//    @Override
//    // entre 8 et 11
//    public int attack(){
//        return (int) ((Math.random()*(11 - 8)) + 8)*enemyLevel/10;
//    }
//
//    @Override
//    //entre 3 et 6
//    public int defend(){
//        return (int) (Math.random()*(6 - 3)) + 3;
//        //return (int) (Math.random()*(10/4 + 1) + 10/4 + 3);
//    }
//}

public class Enemy extends AbstractEnemy{

    public Enemy(String name, int maxHp, int strength) {
        super(name, maxHp, strength);
    }

    //methods
    public int attack() {
        return (int) (Math.random()*(10/4 + 1) + 10/4 + 3);
        //return this.strength * 2*Math.random();
    }

    public int defend() {
        return this.strength*2;
    }
}
