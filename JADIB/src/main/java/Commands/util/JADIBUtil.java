package commands.util;

import java.awt.Color;
import java.util.List;

import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

public class JADIBUtil {
    public static String prefix = "j-";
    public static String avatarUrl = "https://cdn.discordapp.com/avatars/798780702463885322/7212eca92b0b89454ad9190a63f11e64.png?size=256";
    public static Color color = new Color(111, 78, 55);
    public static User getFirstMention(Message message) {
        List<User> users = message.getMentionedUsers();
        return users.get(0);
    }


    public static EmbedBuilder createBasicEmbed(String title, String author, String description, Color color) {
        EmbedBuilder embed = new EmbedBuilder();
        if (title != null) {
            embed.setTitle(title);
        }
        if (author != null) {
            embed.setAuthor(author);
        }
        if (description != null) {
            embed.setDescription(description);
        }
        if (color != null) {
            embed.setColor(color);
        } else {
            embed.setColor(JADIBUtil.color);
        }
        return embed;
    }
}

