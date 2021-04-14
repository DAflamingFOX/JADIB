package commands;

import java.util.ArrayList;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import commandhandler.Command;
import commandhandler.CommandData;
import commandhandler.CommandExecutor;
import commands.cards.Card;
import commands.cards.Deck;
import commands.util.JADIBUtil;

public class BlackJack implements CommandExecutor {

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) {

        // create embed for setting up, send embed, then remove the setting up field
        EmbedBuilder embed = JADIBUtil.createBasicEmbed("Blackjack", null, null, null);
        embed.addField("Setting Up:", "One moment...", false);
        long messageId = data.getChannel().sendMessage(embed).join().getId();
        // remove all fields so the setting up field isnt left in the embed
        embed.removeAllFields();

        // create the deck, dealer, and player
        Deck deck = new Deck(); // preshuffled in constructor
        ArrayList<Card> dealer = new ArrayList();
        ArrayList<Card> player = new ArrayList();

        // give dealer and player two cards for start of game
        dealer.addAll(deck.getCards(2));
        player.addAll(deck.getCards(2));

        // attributes that will be used by the items in the loop to prevent having to do
        // the same action twice
        boolean playerFinished = false;
        int dealerScore = 0;
        int playerScore = 0;
        String dealerCards = "";
        String playerCards = "";

        // only loop while the player isnt finished
        do {
            // set up the score and cards items so that the embed can be sent
            dealerScore = getScore(dealerScore, dealer);
            playerScore = getScore(playerScore, player);
            dealerCards = getDealerCards(playerFinished, dealer, dealerCards, data.getApi());
            playerCards = getAllCards(playerCards, player, data.getApi());

            // send embed and then react to it so the player can interact

        } while (!playerFinished);

        /**
         * setup deck send first set of cards dealer and player react with hit and stand
         * emotes. wait for input or cancel if takes to long repeat hit and stand, or if
         * bust
         * 
         * once stand let dealer play; 16 or below hits, 17 or above stands calculate
         * dealer score, repeat until stand or bust.
         */

    }

    private String getAllCards(String playerCards, ArrayList<Card> player, DiscordApi api) {
        String temp = "";
        for (int i = 0; i < player.size(); i++) {
            temp += player.get(i).getEmote(api);
        }
        return temp;
    }

    private String getDealerCards(boolean playerFinished, ArrayList<Card> player, String dealerCards, DiscordApi api) {
        String temp = "";

        if (playerFinished) {
            for (int i = 0; i < player.size(); i++) {
                temp += player.get(i).getEmote(api);
            }
        } else {
            for (int i = 0; i < player.size() - 1; i++) {
                temp += player.get(i).getEmote(api);
            }
        }

        return temp;
    }

    private int getScore(int score, ArrayList<Card> player) {
        int temp = 0;

        for (int i = 0; i < player.size(); i++) {
            temp += player.get(i).getValue();
        }

        return temp;
    }

}
