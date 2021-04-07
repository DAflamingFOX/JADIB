package commandhandler;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandHandler implements MessageCreateListener {

    private final Command command;
    private final ArrayList<Command> commands;
    private MessageCreateEvent event;


    public CommandHandler(Command command, ArrayList<Command> commands) {
        this.command = command;
        this.commands = commands;
    }


    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        // ignore bot
        if (event.getMessageAuthor().isYourself()) { return; }

        this.event = event;
        CommandData data = new CommandData(event.getApi(), event, this, this.command);
        CommandMessage message = new CommandMessage(event.getMessageContent(), this.command.getPrefix());
        boolean containsAlias = false;

        // check for alias
        if (this.command.getAliases() != null) {
            if (Arrays.stream(this.command.getAliases()).anyMatch(s -> s.equalsIgnoreCase(message.getCaller()))) {
                containsAlias = true;
            }
        }

        if (message.getCaller().equalsIgnoreCase(this.command.getCommand()) || containsAlias) {
            if (event.getMessageContent().startsWith(message.getPrefix())) {
                try {
                    this.command.getExecutor().execute(data, this.commands);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


    }

    public final MessageCreateEvent getEvent() {
        return this.event;
    }
}