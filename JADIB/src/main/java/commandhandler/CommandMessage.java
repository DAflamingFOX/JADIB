package commandhandler;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandMessage {

    private final ArrayList<String> args = new ArrayList<>();
    private final String caller;
    private final String prefix;

    public CommandMessage(String commandMessage, String prefix) {
        this.prefix = prefix;
        commandMessage = commandMessage.replace(prefix, "");
        Arrays.stream(commandMessage.split(" ")).iterator().forEachRemaining(args::add);
        caller = args.get(0);
        args.remove(0);
    }

    public final String getCaller() {
        return this.caller;
    }

    public final String getPrefix() {
        return this.prefix;
    }

    public final String getArg(int index) {
        return this.args.get(index);
    }

    public final ArrayList<String> getArgs() {
        return this.args;
    }


}
