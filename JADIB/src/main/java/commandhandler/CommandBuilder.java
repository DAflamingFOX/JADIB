package commandhandler;

import org.javacord.api.DiscordApi;

import java.util.ArrayList;

public class CommandBuilder {

    private final ArrayList<Command> commands = new ArrayList<>();
    private final String commandPrefix;
    private final DiscordApi api;


    public CommandBuilder(String commandPrefix, DiscordApi api) {
        this.commandPrefix = commandPrefix;
        this.api = api;
    }

    public CommandBuilder(DiscordApi api) {
        this.commandPrefix = "!";
        this.api = api;
    }

    public final CommandBuilder addCommand(String commandString, CommandExecutor executor, String description, String usage) {
        Command command = new Command(commandString, executor, this.api, this.commandPrefix, description == null ? "No description" : description, usage == null ? "No usage" : usage);
        this.commands.add(command);
        return this;
    }

    public final void build() {
        this.commands.forEach(command -> this.api.addMessageCreateListener(new CommandHandler(command, this.commands)));

        // Help command
        // gone, reduced to atoms
        // this.api.addMessageCreateListener(new CommandHandler(new Command(helpCommandTrigger, this.helpCommand, this.api, this.commandPrefix), this.commands));

    }


}
