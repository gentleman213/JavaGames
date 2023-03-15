package com.Hogwart;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
@Getter
@Setter
public class Wizard extends Character{
    public int numAtkUpgrades, numDefUpgrades;
    public Wand wand;
    private House house;
    private List<Potion> potions;
    private ArrayList<AbstractSpell> knownSpells;
    //additional player stats
    public String[] atkUpgrades = {"strength","Power","Might","Godlike Strength"};
    public String[] defUpgrades = {"Heavy Bones","PowerB","MightB","Godlike StrengthB"};
    public Wizard(String name, House house){

        super(name, 100);
        this.numAtkUpgrades = 0;
        this.numDefUpgrades = 0;
        //set additional stats
        this.potions = new ArrayList<>();
        this.knownSpells = new ArrayList<>();
        this.house = house;
    }
    public void equip(Wand wand){
        this.wand = wand;
    }


    public void usePotion(Potion potion) {
            this.hp += potion.getPower();
            this.potions.remove(potion);
    }

    public void addPotion(Potion potion) {
        this.potions.add(potion);
    }

    public void learnSpell(AbstractSpell spell) {
        System.out.println("Wizard " +  " is learning spell: " + spell.getName());
        this.knownSpells.add(spell);
    }

    public boolean castSpell(int index) {
        AbstractSpell spell = this.knownSpells.get(index);
        System.out.println("Wizard " + " is casting spell: " + spell.getName());
        return spell.cast();
    }



    @Override
    public int attack(){
        if (house == House.SLYTHERIN){
            return (int) (20+5*numAtkUpgrades+10);
        }else{
            return (int) (20+5*numAtkUpgrades);
        }
    }

    @Override
    public int defend(){
        if (house == House.GRYFFINDOR){
            return (int) (Math.random()*(10/4 + numDefUpgrades*3 + 3) + 1 + numDefUpgrades*2 + numAtkUpgrades + 10);
        }else {
            return (int) (Math.random() * (10 / 4 + numDefUpgrades * 3 + 3) + 1 + numDefUpgrades * 2 + numAtkUpgrades + 1);
        }
    }


    public void addHealthPoint() {

        maxHp += 20;
    }



    //let the player choose a trait
    public void chooseTrait() {
        GameLogic.clearConsole();
        GameLogic.printHeading("Choose a trait:");
        System.out.println("(1) Attack upgrade");
        System.out.println("(2) Life upgrade");
        //get the player choice:
        int input = GameLogic.readInt("->",2);
        GameLogic.clearConsole();
        if(input ==1){
            GameLogic.printHeading("You chose Attack upgrade!");
            numAtkUpgrades++;
        }else{
            GameLogic.printHeading("You chose Life upgrade !");
            addHealthPoint();
        }
        GameLogic.anythingToContinue();
    }



}
