package com.Hogwart;
import lombok.*;

// Classe représentant un sort
@Getter @Setter
@ToString(callSuper=true)
class Spell extends AbstractSpell {
    private int dmg;

    public Spell(String name, int level, boolean dark, double successRate) {
        super(name, level, dark, successRate);

    }

    public boolean cast() {
        if (this.calculateSuccess()) {
            System.out.println("Casting spell: " + this.getName());
        } else {
            System.out.println("Spell failed: " + this.getName());
            return false;
        }
        return true;
    }
}