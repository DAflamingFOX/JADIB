package commandhandler;

import java.util.ArrayList;

public interface CommandExecutor {


    void execute(final CommandData data, ArrayList<Command> commands);
}
