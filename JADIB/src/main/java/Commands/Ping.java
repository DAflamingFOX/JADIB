package commands;

import java.util.ArrayList;
import commandhandler.*;
import org.javacord.api.entity.message.Message;

public class Ping implements CommandExecutor {

    @Override
    public void execute(CommandData data, ArrayList<Command> command) {
        long ping1, ping2, ms;
        ping1 = System.currentTimeMillis();
        Message msg = data.getChannel().sendMessage("pinging...").join();
        ping2 = System.currentTimeMillis();
        ms = ping2 - ping1;

        msg.edit("Current Ping: ~" + ms + "ms");

    }

}
