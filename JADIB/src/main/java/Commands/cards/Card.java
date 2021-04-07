package commands.cards;

public class Card {
    private String suit;
    private String rank;
    private int value;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return ("[" + suit + ", " + rank + ", " + value + "]");
    }

    public boolean equals(Card c) {
        if (value == c.getValue()){
            return true;
        }
        return false;
    }
}