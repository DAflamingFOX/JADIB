package commandhandler;

import java.io.IOException;
import java.util.ArrayList;

public interface CommandExecutor {

    // had to add IOException for quote command
    void execute(final CommandData data, ArrayList<Command> commands) throws IOException;
}
