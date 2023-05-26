package org.example.model;

import java.util.ArrayList;
import lombok.*;
import org.example.model.Card;


@Getter
@Setter
@AllArgsConstructor
public class Player {
    private String name;
    private ArrayList<Card> cards;
    private int score;
    public Player(String name) {
        this.name = name;
        cards = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(int index) {
        cards.remove(index);
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public int getCardsNumber() {
        return cards.size();
    }

    public void score(int pointsToAdd) {
        score += pointsToAdd;
    }

}
