import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;

import commandhandler.CommandBuilder;
import commands.*;

//import 

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

        // SETUP
        Secret secret = new Secret();

        // LOGIN
        DiscordApi api = new DiscordApiBuilder().setToken(secret.getToken()).login().join();
        api.updateActivity(ActivityType.PLAYING, "Getting Coffee, brb"); // activity for waiting on setup
        System.out.println("Bot Online!");

        //COMMAND BUILDER
        CommandBuilder builder = new CommandBuilder(Util.prefix, api);

        //COMMANDS
        builder.addCommand("help", new Help(), "the help command, displays all commands, or gives instructions on how to use a command.", (Util.prefix + "help [command_name]"));
        builder.addCommand("ping", new Ping(), "Shows the current ping of the bot.", Util.prefix + "ping" );
        builder.addCommand("test", new Test(), "Dev only command, will be removed when bot goes final.", Util.prefix + "test [args]");

        //COMMAND BUILD
        builder.build();

        // STATUS LOOPER
        do {
            int choice = (int) Math.round(Math.random() * 3 + 1);
            switch (choice) {
            case 1: {
                api.updateActivity(ActivityType.WATCHING, "over " + api.getServers().size() + " servers!");
                break;
            }
            case 2: {
                api.updateActivity(ActivityType.WATCHING, "Netflix and chill.");
                break;
            }
            case 3: {
                api.updateActivity(ActivityType.PLAYING, "ungrills your cheese.");
                break;
            }
            case 4: {
                api.updateActivity(ActivityType.PLAYING, "It's pronounced jah-dib.");
            }
            }
            Thread.sleep(300000); // 5 minutes
        } while (true);

    }

}