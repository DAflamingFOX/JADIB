package commands;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.javacord.api.entity.message.embed.EmbedBuilder;

import commandhandler.Command;
import commandhandler.CommandData;
import commandhandler.CommandExecutor;
import commands.economy.Database;
import commands.util.JADIBUtil;

public class Balance implements CommandExecutor {

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) throws IOException {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Balance");
        embed.setColor(JADIBUtil.color);
        embed.setDescription("$" + Database.getBalance(data.getMessageAuthor().getIdAsString()));
        embed.addField("Want more cash?", "Use j-beg, or gamble for more; your can also\n click [here](https://top.gg/bot/798780702463885322/vote) and vote to get $1,000");
        data.getChannel().sendMessage(embed);
    }
}
