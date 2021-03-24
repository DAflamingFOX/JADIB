import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class BotRunner {

    public static void main(String[] args) {
        // Creates a secret object witch holds all things that should not be shown.
        Secret secret = new Secret();

        DiscordApi api = new DiscordApiBuilder().setToken(secret.getToken()).login().join();
        //DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        // Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });

        // Print the invite url of your bot
        System.out.println();
        System.out.println("Logged in!");
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }

}