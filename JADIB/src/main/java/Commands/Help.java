package commands;

import java.util.ArrayList;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import commandhandler.*;
import java.awt.color.*;

public class Help implements CommandExecutor {

    public void execute(CommandData data, ArrayList<Command> commands) {
        String output = "";

        // Make list of commands
        for (int i = 0; i < commands.size(); i++) {
            output += "**" + commands.get(i).getCommand() + "**\n"; // list command
            //output += commands.get(i).getDescription() + "\n"; // list description
            //output += commands.get(i).getUsage() + "\n"; // list usage
            output += "---\n"; // spacer
        }
        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("Help");
        embed.setFooter("JADIB, Made with Javacord and <3", "https://cdn.discordapp.com/avatars/798780702463885322/7212eca92b0b89454ad9190a63f11e64.png?size=256");
        embed.setTimestampToNow();
        embed.addField("Commands", output, false);

        data.getChannel().sendMessage(embed);


    }
}
