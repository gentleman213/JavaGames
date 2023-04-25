package org.tibo.character;
import java.util.ArrayList;
import java.util.List;
import org.tibo.spell.AbstractSpell;
import org.tibo.object.House;
import org.tibo.object.Potion;
import org.tibo.object.Wand;
import lombok.*;
@Getter
@Setter
public class Wizard extends Character {
    public int numAtkUpgrades;
    public Wand wand;
    private House house;
    private List<Potion> potions;
    private ArrayList<AbstractSpell> spells;

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
    public void learnForbiddenSpell(AbstractSpell forbiddenSpell) {
        System.out.println("Wizard " +  " is learning spell: " + forbiddenSpell.getName());
        this.spells.add(forbiddenSpell);
    }



    public String KnownSpells() {
        int i=0;
        String spellText = "";
        for (AbstractSpell spell : spells) {
            i++;
            spellText+= "\n("+i+")" + spell.getName();
        }
        return spellText;
    }

    public List<String> getKnownSpells() {
        List<String> spellList = new ArrayList<>();
        for (int i = 0; i < spells.size(); i++) {
            AbstractSpell spell = spells.get(i);
            spellList.add("(" + (i+1) + ") " + spell.getName());
        }
        return spellList;
    }
    public int attackWithSpell(String spellName) {
        AbstractSpell spell = null;
        for (AbstractSpell s : spells) {
            if (s.getName().equals(spellName)) {
                spell = s;
                break;
            }
        }
        if (spell.cast()) {
            System.out.println("Wizard is casting spell: " + spellName);
            return spell.getDamage();
        } else {
            System.out.println("Spell failed: " + spellName);
            if (spellName =="Forbidden Spell: Avada Kedavra"){
                this.hp = 0;
            }
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
            return (int) (Math.random()*(10-5)) + 5;
        }else {
            return (int) (Math.random() * (5-1)) + 1;
        }
    }


    public void addHealthPoint() {
        maxHp += 20;
    }






}
