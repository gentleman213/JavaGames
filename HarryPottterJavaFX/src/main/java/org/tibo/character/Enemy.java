package org.tibo.character;

public class Enemy extends AbstractEnemy {

    public Enemy(String name, int maxHp, int strength) {
        super(name, maxHp, strength);
    }

    //methods
    public int attack() {
        return (int) ((Math.random()*(10-1))+1)*strength;
    }

    public int defend() {
        return (int) ((Math.random()*(3-1))+1)*strength;
    }
}
