package commands.cards;

import java.util.Collection;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.emoji.KnownCustomEmoji;

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
        if (value == c.getValue()) {
            return true;
        }
        return false;
    }

    public String getEmote(DiscordApi api) {
        Collection<KnownCustomEmoji> jadibEmotes = api.getCustomEmojisByName("JADIB" + value + suit);
        int idSubstring = jadibEmotes.toString().indexOf("id:");
        String emoteId = jadibEmotes.toString().substring(idSubstring + 4, idSubstring + 4 + 18);
        return ("<:JADIB" + value + suit + ":" + emoteId + ">");
    }
}