package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Card {
    private final int value;
    private final int heads;

    /**
     * return the value of the card in String
     * @return
     */
    public String toString() {
        return  value+"";
    }


}

