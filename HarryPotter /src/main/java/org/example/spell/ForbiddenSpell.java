package org.example.spell;
import lombok.*;


@Getter
@Setter
public class ForbiddenSpell extends AbstractSpell {

    private int riskLevel;
    public ForbiddenSpell(String name, int damage,double successRate,int riskLevel) {
        super(name, damage, successRate);
        this.riskLevel = riskLevel;
    }

    @Override
    public boolean cast() {
        System.out.println("Attempting to cast forbidden spell: " + this.getName());
        double randomNumber = Math.random(); // génère un nombre aléatoire entre 0 et 1
        return randomNumber <= this.getSuccessRate();
    }
}
