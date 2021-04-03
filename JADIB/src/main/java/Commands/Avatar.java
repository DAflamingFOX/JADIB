package commands;

import java.util.ArrayList;
import java.util.List;

import org.javacord.api.entity.Icon;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

import commandhandler.Command;
import commandhandler.CommandData;
import commandhandler.CommandExecutor;

public class Avatar implements CommandExecutor {

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) {

        List<User> users = data.getEvent().getMessage().getMentionedUsers();
        EmbedBuilder embed = new EmbedBuilder();

        if (!users.isEmpty()) { // if users is not empty
            Icon avatar = users.get(0).getAvatar();
            embed.setImage(avatar);
            embed.setDescription(users.get(0).getName() + "'s Avatar:");
        } else {
            Icon avatar = data.getMessageAuthor().getAvatar();
            embed.setImage(avatar);
            embed.setDescription(data.getMessageAuthor().getName() + "'s Avatar:");
        }

        embed.setColor(Utillities.color);
        embed.setTimestampToNow();
        data.getChannel().sendMessage(embed);

    }

}
