package utility;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.discordbots.api.client.DiscordBotListAPI;
import org.javacord.api.DiscordApi;
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

    //Todo: update all uses of embeds to use this method to clean up alot and have uniform embeds.
    public static EmbedBuilder createEmbed(DiscordApi api) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(color);
        embed.setTimestampToNow();
        try {
            embed.setFooter(api.getYourself().getName() + "; created by: " + api.getUserById("591484957818617867").get().getName() + " with tlc ❤️", api.getYourself().getAvatar());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return embed;
    }

    public static DiscordBotListAPI getDblApi() {
        DiscordBotListAPI dblapi = new DiscordBotListAPI.Builder().token(Secret.getDblToken()).botId("798780702463885322").build();
        return dblapi;
    }

}

