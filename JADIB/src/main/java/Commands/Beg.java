package commands;

import java.io.IOException;
import java.util.ArrayList;

import commandhandler.Command;
import commandhandler.CommandData;
import commandhandler.CommandExecutor;
import utility.DatabaseAccess;

public class Beg implements CommandExecutor{

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) throws IOException {
        int amount = (int)(Math.random()*10+1);
        data.getChannel().sendMessage(amount + " added to your account");
        DatabaseAccess.addToBalance(amount, data.getMessageAuthor().getIdAsString(), "economy.db");      
    }



}
