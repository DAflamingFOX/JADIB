package commandhandler;

import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;
import java.util.ArrayList;

public class DefaultHelpCommandExecutor implements CommandExecutor {

    @Override
    public void execute(CommandData commandData, ArrayList<Command> commands) {

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(commandData.getApi().getYourself().getName() + " | Help");
        eb.setColor(Color.RED);
        StringBuilder sb = new StringBuilder();

        commands.forEach(command -> sb.append("- ").append("``").append(command.getPrefix()).append(command.getCommand()).append("``").append("\n"));

        eb.setDescription(sb.toString());

        commandData.getEvent().getChannel().sendMessage(eb);
    }
}
