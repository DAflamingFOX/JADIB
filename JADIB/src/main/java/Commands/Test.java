package commands;

import java.util.ArrayList;

import commandhandler.Command;
import commandhandler.CommandData;
import commandhandler.CommandExecutor;

public class Test implements CommandExecutor {

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) {
        // TODO Auto-generated method stub
        ArrayList<String> args = data.getCommandMessage().getArgs();

        for (int i = 0; i < args.size(); i++) {
            data.getChannel().sendMessage("arg " + i + " = " + args.get(i));
        }
        // test for command
        if (commands.contains(args.get(0))) {
            data.getChannel().sendMessage(args.get(0) + "is a command");
        }
    }
}
