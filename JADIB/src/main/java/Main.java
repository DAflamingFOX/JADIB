import org.discordbots.api.client.DiscordBotListAPI;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;

import commandhandler.CommandBuilder;
import commands.*;
import commands.util.JADIBUtil; 

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // JADIB in ASCII art, it looks wrong in code due to having to escape the \
        System.out.println("\n\n      ,--.-,  ,---.                   .=-.-.            ");
        System.out.println("     |==' -|.--.'  \\      _,..---._  /==/_ /  _..---.   ");
        System.out.println("     |==|- |\\==\\-/\\ \\   /==/,   -  \\|==|, | .' .'.-. \\  ");
        System.out.println("   __|==|, |/==/-|_\\ |  |==|   _   _\\==|  |/==/- '=' /  ");
        System.out.println(",--.-'\\=|- |\\==\\,   - \\ |==|  .=.   |==|- ||==|-,   '   ");
        System.out.println("|==|- |=/ ,|/==/ -   ,| |==|,|   | -|==| ,||==|  .=. \\  ");
        System.out.println("|==|. /=| -/==/-  /\\ - \\|==|  '='   /==|- |/==/- '=' ,| ");
        System.out.println("\\==\\, `-' /\\==\\ _.\\=\\.-'|==|-,   _`//==/. /==|   -   /  ");
        System.out.println(" `--`----'  `--`        `-.`.____.' `--`-``-._`.___,'   \n\n");

        // LOGIN
        DiscordApi api = new DiscordApiBuilder().setToken(Secret.getToken()).login().join();
        api.updateActivity(ActivityType.PLAYING, "Getting Coffee, brb"); // activity for waiting on setup
        System.out.println("Bot Online!");

        // COMMAND BUILDER
        CommandBuilder builder = new CommandBuilder(JADIBUtil.prefix, api);

        // COMMANDS
        builder.addCommand("help", new String[] {"info"}, new Help(), "Displays all commands, or shows the usage of a command.", (JADIBUtil.prefix + "help [command_name]"));
        builder.addCommand("ping", new String[] {"beep"}, new Ping(), "Shows the current ping of the bot.", JADIBUtil.prefix + "ping");
        builder.addCommand("links", null, new Links(), "Show all the links for the bot.", JADIBUtil.prefix + "links");
        builder.addCommand("avatar", new String[] {"pfp", "picture"}, new Avatar(), "Show the avatar of a mentioned user.", JADIBUtil.prefix + "avatar [@user]");
        builder.addCommand("quote", new String[] {"inspiration"}, new Quote(), "provide an insperational quote.", JADIBUtil.prefix + "quote");
        builder.addCommand("blackjack", new String[] {"bj"}, new BlackJack(), "Play a game of blackjack against the bot.", JADIBUtil.prefix + "blackjack");
        builder.addCommand("balance", null, new Balance(), "Check your balance.", JADIBUtil.prefix + "balance");
        builder.addCommand("vote", null, new Vote(), "Recieve cash-money for voting!", JADIBUtil.prefix + "vote");

        // COMMAND BUILD
        builder.build();

        // DBL API
        getDblApi().setStats(api.getServers().size());

        // STATUS LOOPER
        do {
            int choice = (int) Math.round(Math.random() * 3 + 1);
            switch (choice) {
            case 1: {
                api.updateActivity(ActivityType.WATCHING, JADIBUtil.prefix + "over " + api.getServers().size() + " servers!");
                break;
            }
            case 2: {
                api.updateActivity(ActivityType.WATCHING, JADIBUtil.prefix + "Netflix and chill.");
                break;
            }
            case 3: {
                api.updateActivity(ActivityType.PLAYING, JADIBUtil.prefix + "ungrills your cheese.");
                break;
            }
            case 4: {
                api.updateActivity(ActivityType.PLAYING, JADIBUtil.prefix + "It's pronounced jah-dib.");
                break;
            }
            case 5: {
                api.updateActivity(ActivityType.WATCHING, "for " + JADIBUtil.prefix);
                break;
            }
            }
            Thread.sleep(300000); // 5 minutes
        } while (true);

    }

    public static DiscordBotListAPI getDblApi() {
        DiscordBotListAPI dblapi = new DiscordBotListAPI.Builder().token(Secret.getDblToken()).botId("798780702463885322").build();
        return dblapi;
    }
}