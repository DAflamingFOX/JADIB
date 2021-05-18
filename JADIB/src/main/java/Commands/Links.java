package commands;

import java.util.ArrayList;

import org.javacord.api.entity.message.embed.EmbedBuilder;

import commands.commandhandler.Command;
import commands.commandhandler.CommandData;
import commands.commandhandler.CommandExecutor;

public class Links implements CommandExecutor{

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Links");  
        embed.setDescription("All the links related to the bot.");
        embed.addField("Support server:", "https://discord.gg/JFuBmKPAsU", false);
        embed.addField("Invite:", "***Main:*** - https://top.gg/bot/798780702463885322#/\n***Backup:*** - https://discord.com/api/oauth2/authorize?client_id=798780702463885322&permissions=2416241729&scope=bot%20applications.commands");
        

        data.getChannel().sendMessage(embed);
    }
    
}
