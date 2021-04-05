package commands.util;

import java.awt.Color;
import java.util.List;

import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;

public class JADIBUtil {
    public static String prefix = "j-";
    public static String avatarUrl = "https://cdn.discordapp.com/avatars/798780702463885322/7212eca92b0b89454ad9190a63f11e64.png?size=256";
    public static Color color = new Color(111, 78, 55);
    public static User getFirstMention(Message message) {
        List<User> users = message.getMentionedUsers();
        return users.get(0);
    }
}

