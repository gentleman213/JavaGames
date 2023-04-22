package org.tibo.character;


import org.tibo.object.Wand;
import lombok.Getter;

@Getter
public class Boss extends AbstractEnemy {

    public Wand wand;
    //Constructor for boss
    public Boss(String name, int maxHp, int strength,Wand wand) {
        super(name, maxHp, strength);
        this.wand = wand;
    }

    //methods
    public int attack() {
        return (int) ((Math.random()*(10-1))+1)*strength;
        //return this.strength * 2*Math.random();
    }
    public void equip(Wand wand){
        this.wand = wand;
    }
    public int defend() {
        return (int) ((Math.random()*(3-1))+1)*strength;
    }
}
