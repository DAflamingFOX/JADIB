import java.io.IOException;
import java.util.ArrayList;

import org.discordbots.api.client.DiscordBotListAPI;

import commandhandler.Command;
import commandhandler.CommandData;
import commandhandler.CommandExecutor;

public class Vote implements CommandExecutor {

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) throws IOException {
        DiscordBotListAPI api = new DiscordBotListAPI.Builder().token().botId("botId").build();       
    }
    
}
