package Commands;

import org.javacord.api.DiscordApi;
import org.javacord.api.event.message.MessageCreateEvent;

public class Command {
    private String[] args;
    public Command(MessageCreateEvent event, DiscordApi api) {
        
    }
    

    protected String[] getArgs() {
        return args;
    }
}
