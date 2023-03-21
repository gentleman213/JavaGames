package com.Hogwart.character;
import java.util.ArrayList;
import java.util.List;
import com.Hogwart.spell.AbstractSpell;
import com.Hogwart.GameLogic;
import com.Hogwart.object.House;
import com.Hogwart.object.Potion;
import com.Hogwart.object.Wand;
import lombok.*;
@Getter
@Setter
public class Wizard extends Character {
    public int numAtkUpgrades;
    public Wand wand;
    private House house;
    private List<Potion> potions;
    private ArrayList<AbstractSpell> spells;
    //additional player stats


    public Wizard(String name, House house){
        super(name, 100);
        this.numAtkUpgrades = 0;
        //set additional stats
        this.potions = new ArrayList<>();
        this.spells = new ArrayList<>();
        this.house = house;
    }
    public void equip(Wand wand){
        this.wand = wand;
    }


    public void usePotion(Potion potion) {
            this.hp += potion.getPower();
            this.potions.remove(0);
            if (hp > maxHp){
                this.hp = maxHp;
            }

    }

    public void addPotion(Potion potion) {
        this.potions.add(potion);
    }

    public void learnSpell(AbstractSpell spell) {
        System.out.println("Wizard " +  " is learning spell: " + spell.getName());
        this.spells.add(spell);
    }



    public int KnownSpells() {
        int i=1;
        for (AbstractSpell spell : spells) {
            System.out.println("("+i+")" + spell.getName());
            i++;
        }
        return i-1;
    }
    public int attackWithSpell(String spellName) {
        AbstractSpell spell = null;
        for (AbstractSpell s : spells) {
            if (s.getName().equals(spellName)) {
                spell = s;
                break;
            }
        }
        GameLogic.clearConsole();
        if (spell.cast()) {
            System.out.println("Wizard is casting spell: " + spellName);
            return spell.getDamage();
        } else {
            System.out.println("Spell failed: " + spellName);
            return 0;
        }
    }


    @Override
    public int attack(){
        if (house == House.SLYTHERIN){
            return (int) (3*numAtkUpgrades+5);
        }else{
            return (int) (3*numAtkUpgrades);
        }
    }

    @Override
    public int defend(){
        if (house == House.GRYFFINDOR){
            return (int) (Math.random()*(15-5)) + 5;
        }else {
            return (int) (Math.random() * (10-1)) + 1;
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
