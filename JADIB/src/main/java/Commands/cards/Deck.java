package commands.cards;

import java.util.ArrayList;

public class Deck {
    private Card[] cards;
    private int size;
    private int currCard = 0;

    public Deck() {
        cards = new Card[52];
        size = 0;

        String[] suit = { "Clubs", "Diamonds", "Hearts", "Spades" };
        String[] rank = { "ace", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack",
                "queen", "king" };
        int[] value = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

        for (int i = 0; i < suit.length; i++) {
            for (int j = 0; j < value.length; j++) {
                add(suit[i], rank[j], value[j]);
            }
        }
        shuffle();
    }

    public String toString() {
        String tmp = "";
        for (int i = 0; i < size; i++) {
            tmp += "[" + cards[i].getSuit() + ", " + cards[i].getRank() + ", " + cards[i].getValue() + "]" + "\n";
        }
        return tmp;
    }

    public void add(String suit, String rank, int value) {
        cards[size] = new Card(suit, rank, value);
        size++;
    }

    public void shuffle() {
        currCard = 0;
        for (int i = 0; i < 100; i++) {
            int firstCard = (int) (Math.random() * 52);
            int secondCard = (int) (Math.random() * 52);

            Card temp;

            temp = cards[firstCard];
            cards[firstCard] = cards[secondCard];
            cards[secondCard] = temp;
        }
    }

    public Card getTopCard() {
        if (currCard > size) {
            shuffle();
            currCard = 0;
        }
        Card tmp = cards[currCard];
        currCard++;
        return tmp;
    }

    public ArrayList<Card> getCards(int numCards) {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < numCards; i++) {
            cards.add(getTopCard());
        }
        return cards;
    }
}
