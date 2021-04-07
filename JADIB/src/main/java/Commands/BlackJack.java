package commands;

import java.io.IOException;
import java.util.ArrayList;

import commandhandler.Command;
import commandhandler.CommandData;
import commandhandler.CommandExecutor;
import commands.cards.Deck;

public class BlackJack implements CommandExecutor {

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) throws IOException {
        Deck deck = new Deck();        
    }

}
