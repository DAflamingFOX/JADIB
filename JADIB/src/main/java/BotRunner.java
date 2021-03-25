import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;

public class BotRunner {

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
        api.updateActivity(ActivityType.PLAYING, "booting up...");
        System.out.println("Bot Online!");

        // MESSAGE LISTENER
        api.addMessageCreateListener(event -> {
            // checks for prefix
            if (!event.getMessageContent().toLowerCase().startsWith(Util.prefix)
                    || event.getMessageAuthor().isBotUser()) {
                return;
            } else if (event.getMessageContent().toLowerCase().startsWith(Util.prefix)) {
                System.out.println("command || " + event.getMessageContent() + " || was requested"); // for debug rn
                // passes command handler event

            }
        });

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
                api.updateActivity(ActivityType.PLAYING, "It's pronounced jay dib.");
            }
            }
            Thread.sleep(60000); // a minute
        } while (true);

    }

}