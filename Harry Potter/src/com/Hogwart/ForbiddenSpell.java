package com.Hogwart;
import lombok.*;
@Getter @Setter
@ToString(callSuper=true)
class ForbiddenSpell extends AbstractSpell {
    private int riskLevel;

    public ForbiddenSpell(String name, int level,double successRate, boolean dark, int riskLevel) {
        super(name, level, dark,successRate );
        this.riskLevel = riskLevel;
    }

    public boolean cast() {
        System.out.println("Attempting to cast forbidden spell: " + this.getName());
        return true;
    }
}