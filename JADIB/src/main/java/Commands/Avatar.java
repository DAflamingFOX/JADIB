package commands;

import java.util.ArrayList;

import org.javacord.api.entity.Icon;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

import commandhandler.Command;
import commandhandler.CommandData;
import commandhandler.CommandExecutor;
import utility.JADIBUtil;

public class Avatar implements CommandExecutor {

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) {

        User mentioned = JADIBUtil.getFirstMention(data.getEvent().getMessage());
        EmbedBuilder embed = new EmbedBuilder();
        
        if (mentioned != data.getMessageAuthor()) { // if users is not empty
            Icon avatar = mentioned.getAvatar();
            embed.setImage(avatar);
            embed.setDescription(mentioned.getName() + "'s Avatar:");
        } else {
            Icon avatar = data.getMessageAuthor().getAvatar();
            embed.setImage(avatar);
            embed.setDescription("<@" + data.getMessageAuthor().getIdAsString() + ">'s Avatar:");
        }

        embed.setColor(JADIBUtil.color);
        embed.setTimestampToNow();
        data.getChannel().sendMessage(embed);

    }

}
