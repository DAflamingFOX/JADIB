package commands;

import java.io.IOException;
import java.util.ArrayList;

import org.javacord.api.entity.message.embed.EmbedBuilder;

import commandhandler.Command;
import commandhandler.CommandData;
import commandhandler.CommandExecutor;
import utility.DatabaseAccess;
import utility.JADIBUtil;

public class Balance implements CommandExecutor {

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) throws IOException {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Balance");
        embed.setColor(JADIBUtil.color);
        embed.setDescription("$" + DatabaseAccess.getBalance(data.getMessageAuthor().getIdAsString()));
        embed.addField("Want more cash?", "Use `"+JADIBUtil.prefix+"beg`, play games, or you can also\n click [here](https://top.gg/bot/798780702463885322/vote) to vote and get $1,000 cash-money");
        data.getChannel().sendMessage(embed);
        
    }
}
