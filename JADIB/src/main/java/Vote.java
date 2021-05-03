import java.io.IOException;
import java.util.ArrayList;

import commandhandler.Command;
import commandhandler.CommandData;
import commandhandler.CommandExecutor;

public class Vote implements CommandExecutor {

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) throws IOException {
 


        Main.getDblApi().hasVoted(data.getMessageAuthor().getIdAsString()).whenComplete((hasVoted, e) -> {
            // ! VOTE TIMES NEEDS TO BE CHECKED, perhaps another table?
            // TODO: add lastVoteTimeMilis column to table; then check if 12 hours have passed sense last time before giving user more cash-money.
            if(hasVoted && (CommandDatabaseBackend.getLastVoteTime() - System.currentTimeMillis() > ))
                data.getChannel().sendMessage("You have voted! You should now receive $1000 cash-money");
                // TODO: actually give user money.
            else
                data.getChannel().sendMessage("You have not voted yet!");
                // TODO: refer to the voting link, and reminding them to run j-vote again to revieve their money
        });
    }
    
}
