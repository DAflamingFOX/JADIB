package commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import org.javacord.api.entity.emoji.KnownCustomEmoji;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import commandhandler.Command;
import commandhandler.CommandData;
import commandhandler.CommandExecutor;

public class SelfTest implements CommandExecutor {

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) {

        data.getChannel().type();
        long msgId = data.getChannel().sendMessage("Testing emotes").join().getId();
        try {
            data.getChannel().getMessageById(msgId).get().edit("edit test:");
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Collection<KnownCustomEmoji> jadibEmotes = data.getApi().getCustomEmojisByName("JADIB1Spades");
        int idSubstring = jadibEmotes.toString().indexOf("id:");
        String emoteId = jadibEmotes.toString().substring(idSubstring + 4, idSubstring + 4 + 18);
        data.getChannel().sendMessage("<:JADIB1Spades:" + emoteId + ">");

        // test embed sending and editing
        EmbedBuilder embed = new EmbedBuilder();
        embed = buildEmbed(embed);
        long msgId2 = data.getChannel().sendMessage(embed).join().getId();

        embed.addField("testing Edit", "body", false);
        try {
            data.getChannel().getMessageById(msgId2).get().edit(embed);
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private EmbedBuilder buildEmbed(EmbedBuilder embed) {
        EmbedBuilder maker = new EmbedBuilder();
        maker.setTitle("SelfTest");
        maker.addField("Field", "Body", false);
        maker.setTimestampToNow();
        maker.setDescription("description");    
        return maker;
    }

}
