package commands;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import commandhandler.Command;
import commandhandler.CommandData;
import commandhandler.CommandExecutor;
import commands.cards.Card;
import commands.cards.Deck;
import utility.JADIBUtil;

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
		// dealer.addAll(deck.getCards(2));
		// player.addAll(deck.getCards(2));
		dealer.addAll(deck.getCards(2));
		player.addAll(deck.getCards(2));

		// attributes that will be used by the items in the loop to prevent having to do
		// the same action twice
		boolean playerFinished = false;
		int dealerScore = 0;
		int playerScore = 0;
		String dealerCards = "";
		String playerCards = "";
		String playerChoice = "";

		playerScore = getScore(player);
		dealerScore = getScore(dealer);
		dealerCards = getDealerCards(playerFinished, dealer, data.getApi()); // dealer cards also sets the score (hiding
																				// the score of one card)
		playerCards = getAllCards(player, data.getApi());

		embed.addField(String.valueOf("Dealer: " + dealerScore), dealerCards, true);
		embed.addField(String.valueOf("Player: " + playerScore), playerCards, true);

		message.edit(embed).join();

		// time the loop starts, used for timeout
		long startTime = System.currentTimeMillis();
		// only loop while the player isnt finished
		do {
			embed.removeAllFields();
			playerChoice = "";

			// adds the reactions
			message.addReactions(new String[] { "👊", "🛑" }).join();

			try {
				for (User user : message.getReactionByEmoji("👊").get().getUsers().get()) {
					if (user.getId() == data.getMessageAuthor().getId()) {
						// this code is activated if the user hits
						playerChoice = "hit";
					}
				}
				for (User user : message.getReactionByEmoji("🛑").get().getUsers().get()) {
					if (user.getId() == data.getMessageAuthor().getId()) {
						// this code is activated if the user stands
						playerChoice = "stand";
					}
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}

			switch (playerChoice) {
			case "hit": {
				message.removeAllReactions().join();

				player.add(deck.getTopCard());
				playerScore = getScore(player);
				playerCards = getAllCards(player, data.getApi());

				embed.removeAllFields();

				embed.addField(String.valueOf("Dealer :" + dealerScore), dealerCards, true);
				embed.addField(String.valueOf("Player :" + playerScore), playerCards, true);

				if (playerScore > 21) {
					message.edit(embed).join();
				} else {
					message.edit(embed).join();
					break;
				}
			}
			case "stand": {
				playerFinished = true;
				message.removeAllReactions();
				break;
			}
			}

			if (System.currentTimeMillis() - startTime > (15 * 1000)) {
				message.removeAllReactions();
				sendTimeoutMessage(data.getEvent());
				break;
			} else if (playerFinished) {
				// Im sure I can rewrite this but for now it fixes the issue of the following
				// else setting true.
			} else {
				playerFinished = false;
			}
		} while (!playerFinished);

		// dealer logic
		while (dealerScore < 16 && !(dealerScore > 21) && playerFinished) { // while below 16 and not above 21; also
																			// only run if the player actually finish
																			// their turn
			dealer.addAll(deck.getCards(1));
			dealerCards = getDealerCards(playerFinished, dealer, data.getApi());
			dealerScore = getScore(dealer);

			embed.removeAllFields();

			embed.addField(String.valueOf(dealerScore), dealerCards, true);
			embed.addField(String.valueOf(playerScore), playerCards, true);
			message.edit(embed).join();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// if the dealer didnt need to add cards it never updated.
		embed.removeAllFields();
		dealerCards = getDealerCards(playerFinished, dealer, data.getApi());
		embed.addField("Dealer: " + dealerScore, dealerCards, true);
		embed.addField("Player: " + playerScore, playerCards, true);
		message.edit(embed).join();

		// calculate endgame winner/loser/co-loser
		embed.addField(getOutcome(playerScore, dealerScore), "gg!", false);
		message.edit(embed);
		// if/when economy introduced, if win, take whatever bet was and if win, give x2
		// if lose, do nothing take bet amount
	}

	private String getOutcome(int player, int dealer) {
		String temp = new String();
		if (dealer > player && dealer <= 21 || player > 21) {
			temp = "You lose!";
		} else if (player > dealer && player <= 21 || dealer > 21) {
			temp = "You win!";
		} else if (dealer == player && !(dealer > 21 && player > 21)) {
			temp = "We tie!";
		}
		if (dealer > 21 && player > 21) {
			temp = "Nobody wins! Both busted!";
		}
		if (dealer > 21 && !temp.equals("Both bust!")) {
			temp += " Dealer busted!";
		}
		if (player > 21 && !temp.equals("Both bust!")) {
			temp += " You busted!";
		}

		return temp;
	}

	private void sendTimeoutMessage(MessageCreateEvent messageCreateEvent) {
		messageCreateEvent.getChannel().sendMessage("Sorry, you took to long!");
	}

	private String getAllCards(ArrayList<Card> player, DiscordApi api) {
		String temp = "";

		for (int i = 0; i < player.size(); i++) {
			temp += player.get(i).getEmote(api);
		}

		return temp;
	}

	private String getDealerCards(boolean playerFinished, ArrayList<Card> player, DiscordApi api) {
		String temp = "";

		if (playerFinished) {
			for (int i = 0; i < player.size(); i++) {

				temp += player.get(i).getEmote(api);
			}
		} else {
			for (int i = 0; i < player.size() - 1; i++) {
				temp += player.get(i).getEmote(api);
			}
			temp += ":flower_playing_cards:";
		}

		return temp;
	}

	private int getScore(ArrayList<Card> player) {
		int temp = 0;
		ArrayList<Integer> aceList = new ArrayList<Integer>();
		for (int i = 0; i < player.size(); i++) {
			if (player.get(i).getValue() == 1) {
				aceList.add(i);
			} else if (player.get(i).getValue() > 10) {
				temp += 10;
			} else {
				temp += player.get(i).getValue();
			}

		}

		for (int j = 0; j < aceList.size(); j++) {
			if (temp < (10 - aceList.size() + 1)) {
				temp += 11;
			} else {
				temp += 1;
			}
		}

		return temp;
	}

}
