package org.example.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Getter
@Setter
public class Pile {
    private ArrayList<Card> cards;

    public Pile() {
        cards = new ArrayList<>();
    }

    /**
     * add a card to a pile
     * @param card
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    public int getCardsNumber() {
        return cards.size();
    }

    public Card getCard(int index) {
        return cards.get(index);
    }


    public int totalHeads() {
        int totalHeads = 0;
        for (Card card : getCards()) {
            totalHeads += card.getHeads();
        }
        return totalHeads;
    }

    public String toString() {
        StringBuilder resultat = new StringBuilder();
        for (Card card : cards) {
            resultat.append(card.toString()).append("\n");
        }
        return resultat.toString();
    }
}
