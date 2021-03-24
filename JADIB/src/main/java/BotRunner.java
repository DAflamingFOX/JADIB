import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;

public class BotRunner {

    public static void main(String[] args) throws InterruptedException {
        // Creates a secret object witch holds all things that should not be shown.
        Secret secret = new Secret();

        DiscordApi api = new DiscordApiBuilder().setToken(secret.getToken()).login().join();
        api.updateActivity(ActivityType.PLAYING, "booting up...");
        System.out.println();
        System.out.println("Logged in!");

        api.addMessageCreateListener(message -> {
            if (message.getMessageContent().startsWith(Util.prefix)) {
                System.out.println("command || " + message.getMessageContent() + " || was requested");
            }
        });
        // activity looper
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
                api.updateActivity(ActivityType.PLAYING, "It's pronounced ja bid.");
            }
            }
            System.out.println("CurrStatus - " + choice);
            Thread.sleep(60000); // a minute
        } while (true);
        /*
         * api.addMessageCreateListener(event -> { if
         * (event.getMessageContent().equalsIgnoreCase("!ping")) {
         * event.getChannel().sendMessage("Pong!"); } });
         */
        // Print the invite url of your bot

        // System.out.println("You can invite the bot by using the following url: " +
        // api.createBotInvite());
    }

}