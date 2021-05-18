package src;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

import commands.Avatar;
import commands.Balance;
import commands.BlackJack;
import commands.Help;
import commands.Links;
import commands.Ping;
import commands.Quote;
import commands.commandhandler.CommandBuilder;
import threads.DBLApiThread;
import threads.NewUserProccessingThread;
import threads.StatusLoopThread;
import utility.JADIBUtil;
import utility.Secret;

public class Main {
    private static DiscordApi api;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\nStarting.");

        // discord api login
        buildApi(Secret.getToken());

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
        new DBLApiThread().start();

    }

    private static void buildApi(String token) {
        System.out.println("Building api.");
        api = new DiscordApiBuilder().setToken(token).setAllIntentsExcept(Intent.GUILD_PRESENCES).login().join();
        System.out.println("\nBot Online:\n" +
                           "Username:\t" + api.getYourself().getDiscriminatedName() +
                           "\nShards:\t\t" + api.getTotalShards() +
                           "\nServers:\t" + api.getServers().size() +
                           "\nMembers:\t" + api.getCachedUsers().size());
    }

    public static DiscordApi getApi() {
        return api;
    }

}