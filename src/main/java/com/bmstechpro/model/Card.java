package com.bmstechpro.model;
/* library-app
 * @created 10/08/2022
 * @author Konstantin Staykov
 */

public final class Card {
    private final CardEnum cardEnum;
    private final Suit suit;
    public Card(CardEnum cardEnum, Suit suit){
        this.cardEnum = cardEnum;
        this.suit = suit;
    }

    public CardEnum getCardEnum() {
        return cardEnum;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                cardEnum.name() +
                ", "+ suit.name() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (cardEnum != card.cardEnum) return false;
        return suit == card.suit;
    }

    @Override
    public int hashCode() {
        int result = cardEnum.hashCode();
        result = 31 * result + suit.hashCode();
        return result;
    }
}
