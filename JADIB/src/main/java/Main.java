import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.message.Message;
import org.javacord.api.listener.message.MessageCreateListener;

import commandhandler.CommandBuilder;
import commands.Avatar;
import commands.Balance;
import commands.BlackJack;
import commands.Help;
import commands.Links;
import commands.Ping;
import commands.Quote;
import commands.Vote;
import commands.util.JADIBUtil;
import commands.util.Secret; 

import threads.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // discord api login
        DiscordApi api = new DiscordApiBuilder().setToken(Secret.getToken()).login().join();
        api.updateActivity(ActivityType.PLAYING, "Getting Coffee, brb"); // activity for waiting on setup
        System.out.println("Bot Online!");

        // command handler
        CommandBuilder builder = new CommandBuilder(JADIBUtil.prefix, api);
        builder.addCommand("help", new String[] {"info"}, new Help(), "Displays all commands, or shows the usage of a command.", (JADIBUtil.prefix + "help [command_name]"));
        builder.addCommand("ping", new String[] {"beep"}, new Ping(), "Shows the current ping of the bot.", JADIBUtil.prefix + "ping");
        builder.addCommand("links", null, new Links(), "Show all the links for the bot.", JADIBUtil.prefix + "links");
        builder.addCommand("avatar", new String[] {"pfp", "picture"}, new Avatar(), "Show the avatar of a mentioned user.", JADIBUtil.prefix + "avatar [@user]");
        builder.addCommand("quote", new String[] {"inspiration"}, new Quote(), "provide an insperational quote.", JADIBUtil.prefix + "quote");
        builder.addCommand("blackjack", new String[] {"bj"}, new BlackJack(), "Play a game of blackjack against the bot.", JADIBUtil.prefix + "blackjack");
        builder.addCommand("balance", null, new Balance(), "Check your balance.", JADIBUtil.prefix + "balance");
        builder.build();

        // dbl api
        JADIBUtil.getDblApi().setStats(api.getServers().size());

        // start other threads
        new NewUserProccessingThread().start();
        new StatusLoopThread().start();
        new VoteCheckThread().start();

    }

}