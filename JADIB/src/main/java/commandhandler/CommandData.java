package commandhandler;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageCreateEvent;

public class CommandData {

    public final DiscordApi api;
    private final MessageCreateEvent messageCreateEvent;
    private final CommandHandler handler;
    private final Command command;


    public CommandData(DiscordApi api, MessageCreateEvent messageCreateEvent, CommandHandler handler, Command command) {
        this.api = api;
        this.messageCreateEvent = messageCreateEvent;
        this.handler = handler;
        this.command = command;
    }


    public final DiscordApi getApi() {
        return this.api;
    }

    public final MessageCreateEvent getEvent() {
        return this.messageCreateEvent;
    }

    public final MessageAuthor getMessageAuthor() {
        return this.getEvent().getMessageAuthor();
    }

    public final CommandHandler getHandler() {
        return this.handler;
    }

    public final CommandMessage getCommandMessage() {
        return new CommandMessage(this.getEvent().getMessageContent(), this.command.getPrefix());
    }

    public final ServerTextChannel getChannel() {
        return this.getEvent().getServerTextChannel().get();
    }


}
