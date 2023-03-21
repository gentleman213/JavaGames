package com.Hogwart.spell;
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

    public boolean calculateSuccess() {
        double randomNumber = Math.random(); // génère un nombre aléatoire entre 0 et 1
        return randomNumber <= this.getSuccessRate();
    }
}


/*
import lombok.Getter;
        import lombok.Setter;

        import java.util.ArrayList;
        import java.util.List;

@Getter
@Setter
abstract class AbstractSpell {
    private String name;
    private int damage;

    public AbstractSpell(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    abstract boolean cast();
}

@Getter
@Setter
class Spell extends AbstractSpell {
    public Spell(String name, int damage) {
        super(name, damage);
    }

    @Override
    boolean cast() {
        // Ajoutez ici une logique pour déterminer si le sort réussit ou échoue
        // Vous pouvez utiliser une méthode aléatoire pour simuler l'échec ou la réussite
        // par exemple : return new Random().nextInt(2) == 0;
        return true;
    }
}

@Getter
@Setter
class ForbiddenSpell extends AbstractSpell {
    public ForbiddenSpell(String name, int damage) {
        super(name, damage);
    }

    @Override
    boolean cast() {
        // Ajoutez ici une logique pour déterminer si le sort réussit ou échoue
        // Vous pouvez utiliser une méthode aléatoire pour simuler l'échec ou la réussite
        // par exemple : return new Random().nextInt(2) == 0;
        return true;
    }
}

@Getter
class Wizard {
    private List<AbstractSpell> spells = new ArrayList<>();

    public void learnSpell(AbstractSpell spell) {
        spells.add(spell);
    }

    public int attackWithSpell(AbstractSpell spell) {
        if (spell.cast()) {
            return spell.getDamage();
        } else {
            return 0;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Wizard harryPotter = new Wizard();

        Spell expelliarmus = new Spell("Expelliarmus", 10);
        harryPotter.learnSpell(expelliarmus);

        ForbiddenSpell avadaKedavra = new ForbiddenSpell("Avada Kedavra", 100);
        harryPotter.learnSpell(avadaKedavra);

        int damage = harryPotter.attackWithSpell(expelliarmus);
        System.out.println("Expelliarmus a infligé " + damage + " dégâts");

        damage = harryPotter.attackWithSpell(avadaKedavra);
        System.out.println("Avada Kedavra a infligé " + damage + " dégâts");
    }
}
*/
