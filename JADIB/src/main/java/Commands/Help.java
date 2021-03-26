package commands;

import java.util.ArrayList;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import commandhandler.*;
import java.awt.Color;

public class Help implements CommandExecutor {

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) {

        // embed
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Help");
        embed.setColor(new Color(111, 78, 55));
        embed.setThumbnail("https://cdn.discordapp.com/avatars/798780702463885322/7212eca92b0b89454ad9190a63f11e64.png?size=256");
        embed.setDescription("This is a list of all available commands!");
        embed.setFooter("Made by DAflamingFOX#2140, with Javacord and <3", "https://cdn.discordapp.com/avatars/798780702463885322/7212eca92b0b89454ad9190a63f11e64.png?size=256");
        embed.setTimestampToNow();

        // the command aspect
        embed.addField("Commands", makeCommandList(data, commands, new String()), false);

        // send embed message
        data.getChannel().sendMessage(embed);


    }

    private String makeCommandList(CommandData data, ArrayList<Command> commands, String string) {
        for(int i = 0; i < commands.size();) {
            //if data.commandData
        }
        return null;
    }
}
