package com.Hogwart;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
@Getter
@Setter
public class Wizard extends Character{
    public int numAtkUpgrades, numDefUpgrades;
    public Wand wand;
    private List<Potion> potions;

    //additional player stats
    public String[] atkUpgrades = {"strength","Power","Might","Godlike Strength"};
    public String[] defUpgrades = {"Heavy Bones","PowerB","MightB","Godlike StrengthB"};
    public Wizard(String name){

        super(name, 100);
        this.numAtkUpgrades = 0;
        this.numDefUpgrades = 0;
        //set additional stats
        this.potions = new ArrayList<>();
    }
    public void equip(Wand wand){
        this.wand = wand;
    }

    public void usePotion(Potion potion) {
            //this.hp += potion.getPower();
        this.hp += 10;
    }

    public void addPotion(Potion potion) {
        this.potions.add(potion);
    }



    @Override
    public int attack(){
        return (int) (Math.random()*(10/4 + numAtkUpgrades*3 + 3) + 10/10 + numAtkUpgrades*2 + numDefUpgrades + 1);
    }

    @Override
    public int defend(){
        return (int) (Math.random()*(10/4 + numDefUpgrades*3 + 3) + 10/10 + numDefUpgrades*2 + numAtkUpgrades + 1);
    }

    //let the player choose a trait
    public void chooseTrait() {
        GameLogic.clearConsole();
        GameLogic.printHeading("Choose a trait:");
        System.out.println("(1)"+atkUpgrades[numAtkUpgrades]);
        System.out.println("(2)"+defUpgrades[numDefUpgrades]);
        //get the player choice:
        int input = GameLogic.readInt("->",2);
        GameLogic.clearConsole();
        if(input ==1){
            GameLogic.printHeading("You chose"+atkUpgrades[numAtkUpgrades] + "!");
            numAtkUpgrades++;
        }else{
            GameLogic.printHeading("You chose"+defUpgrades[numDefUpgrades]+ "!");
            numDefUpgrades++;
        }
        GameLogic.anythingToContinue();
    }


    public List<Potion> getPotions() {
        return potions;
    }
}
