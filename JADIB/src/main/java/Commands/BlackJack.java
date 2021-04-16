package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.emoji.Emoji;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

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
        Message message = data.getChannel().sendMessage(embed).join();

        // remove all fields so the setting up field isnt left in the embed
        embed.removeAllFields();

        // create the deck, dealer, and player
        Deck deck = new Deck(); // preshuffled in constructor
        ArrayList<Card> dealer = new ArrayList<Card>();
        ArrayList<Card> player = new ArrayList<Card>();

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

        // time the loop starts, used for timeout
        long startTime = System.currentTimeMillis();
        // only loop while the player isnt finished
        do {
            // set up the score and cards items so that the embed can be sent
            dealerScore = getScore(dealerScore, dealer);
            playerScore = getScore(playerScore, player);
            dealerCards = getDealerCards(playerFinished, dealer, dealerCards, data.getApi());
            playerCards = getAllCards(playerCards, player, data.getApi());

            // add the score/card fields
            embed.addField(String.valueOf(dealerScore), dealerCards, true);
            embed.addField(String.valueOf(playerScore), playerCards, true);

            // send message and react with hit and stay emotes

                message.edit(embed).join();
         
                // adds the reactions
                message.addReactions(new String[] { "👊", "🛑" }).join();

                try {
                    for (User user : message.getReactionByEmoji("👊").get().getUsers().get()) {
                        if (user.getId() == data.getMessageAuthor().getId()) {
                            // this code is activated if the user hits
                            data.getChannel().sendMessage("you hit.");
                        }
                    }
                    for (User user : message.getReactionByEmoji("🛑").get().getUsers().get()) {
                        if (user.getId() == data.getMessageAuthor().getId()) {
                            // this code is activated if the user stands
                            data.getChannel().sendMessage("you stand.");
                        }
                    }
                } catch (InterruptedException | ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
               

            
            
            // hardcoding finish for now
            if (System.currentTimeMillis() - startTime > (15*1000) || playerFinished) {
                playerFinished = true;
                sendTimeoutMessage(data.getEvent());
            } else {
                playerFinished = false;
            }
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

    private void sendTimeoutMessage(MessageCreateEvent messageCreateEvent) {
        messageCreateEvent.getChannel().sendMessage("Sorry, you took to long!");
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
