package commands;

import java.util.ArrayList;

import org.javacord.api.entity.message.Message;

import commands.commandhandler.*;

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
