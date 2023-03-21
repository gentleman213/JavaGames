package com.Hogwart.spell;
import lombok.*;


@Getter
@Setter
class ForbiddenSpell extends AbstractSpell {

    private int riskLevel;
    public ForbiddenSpell(String name, int damage,double successRate,int riskLevel) {
        super(name, damage, successRate);
        this.riskLevel = riskLevel;
    }

    @Override
    public boolean cast() {
        System.out.println("Attempting to cast forbidden spell: " + this.getName());
        // Ajoutez ici une logique pour déterminer si le sort réussit ou échoue
        // Vous pouvez utiliser une méthode aléatoire pour simuler l'échec ou la réussite
        // par exemple : return new Random().nextInt(2) == 0;
        return true;
    }
}
