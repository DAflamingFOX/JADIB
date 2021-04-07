package commands.cards;

public class Deck {
    private Card[] cards;
    private int size;

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
        for (int i = 0; i < 100; i++) {
            int firstCard = (int)(Math.random()*52);
            int secondCard = (int)(Math.random()*52);

            Card temp;

            temp = cards[firstCard];
            cards[firstCard] = cards[secondCard];
            cards[secondCard] = temp;
        }
    }
}
