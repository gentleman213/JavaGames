package org.example.character;
import org.example.object.House;

import java.util.Random;

public class SortingHat {
    private Random random;

    public SortingHat() {
        random = new Random();
    }

    public House assignHouse() {
        int randNum = random.nextInt(4);
        return House.values()[randNum];
    }
}
