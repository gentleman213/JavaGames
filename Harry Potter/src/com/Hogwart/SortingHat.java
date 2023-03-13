package com.Hogwart;

import java.util.Random;

public class SortingHat {
    private House[] houses;

    public SortingHat() {
        // Initialisation des maisons de Poudlard
        this.houses = new House[] {
                new House("Gryffondor"),
                new House("Serdaigle"),
                new House("Poufsouffle"),
                new House("Serpentard")
        };
    }

    public House assignHouse(Wizard wizard) {
        // Algorithme de tri basé sur la baguette magique du sorcier
        // ...

        // Retourne une maison assignée au hasard
        return houses[new Random().nextInt(houses.length)];
    }
}
