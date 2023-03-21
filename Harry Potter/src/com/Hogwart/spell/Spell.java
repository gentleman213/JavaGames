package com.Hogwart.spell;
import lombok.*;

@Getter
@Setter
public
class Spell extends AbstractSpell {
    public Spell(String name, int damage,double successRate) {
        super(name, damage,successRate);
    }

    @Override
    public boolean cast() {
        double randomNumber = Math.random(); // génère un nombre aléatoire entre 0 et 1
        return randomNumber <= this.getSuccessRate();
    }
}
