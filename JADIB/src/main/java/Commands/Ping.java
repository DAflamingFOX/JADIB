package Commands;

import org.javacord.api.DiscordApi;
import org.javacord.api.event.message.MessageCreateEvent;

public class Ping extends Command{

    public Ping(MessageCreateEvent event, DiscordApi api) {
        super(event, api);
    }
    
    
}
