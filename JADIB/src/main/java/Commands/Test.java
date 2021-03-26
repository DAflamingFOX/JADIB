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
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getCommand().equals(args.get(0)))
                data.getChannel().sendMessage(commands.get(i).getCommand() + " is a command that matches args 0");
            else
                data.getChannel().sendMessage(commands.get(i).getCommand() + " is a command that does not match args 0");
        }
    }
}
