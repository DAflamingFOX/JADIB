package commandhandler;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.ArrayList;

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

        if (message.getCaller().equalsIgnoreCase(this.command.getCommand())) {
            if (event.getMessageContent().startsWith(message.getPrefix())) {
                this.command.getExecutor().execute(data, this.commands);
            }
        }


    }

    public final MessageCreateEvent getEvent() {
        return this.event;
    }
}
