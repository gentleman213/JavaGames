package org.example.spell;
import lombok.*;

@Getter
@Setter
public abstract class AbstractSpell {
    private String name;
    private int damage;
    private double successRate;

    public AbstractSpell(String name, int damage, double successRate) {
        this.name = name;
        this.damage = damage;
        this.successRate = successRate;
    }

    public abstract boolean cast();
}

