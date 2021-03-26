package commands;

import java.util.ArrayList;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import commandhandler.*;
import java.awt.Color;

public class Help implements CommandExecutor {
    // sets list as default
    private boolean isList = true;

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) {
        // setup
        ArrayList<String> args = data.getCommandMessage().getArgs();
        String cmdList = makeCommandList(commands, args);

        // embed
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Help");
        embed.setColor(new Color(111, 78, 55));
        embed.setThumbnail("https://cdn.discordapp.com/avatars/798780702463885322/7212eca92b0b89454ad9190a63f11e64.png?size=256");
        embed.setDescription("This is a list of all available commands!");
        embed.setFooter("Made by DAflamingFOX#2140, with Javacord and <3", "https://cdn.discordapp.com/avatars/798780702463885322/7212eca92b0b89454ad9190a63f11e64.png?size=256");
        embed.setTimestampToNow();

        // change command based on list or usage
        if(isList) {
            embed.addField("Command(s):", cmdList, false);
            embed.addField("Need command usage?", "Type `j-help [command_name]`", false);
            embed.addField("Still need help?", "Type `j-links` for links to the support server.", false);
        }
        else {
            embed.addField("Usage:", cmdList, false);
            embed.addField("Still need help?", "Type `j-links` for links to the support server.", false);
        }
        
        // send embed message
        data.getChannel().sendMessage(embed);

    }

    private String makeCommandList(ArrayList<Command> commands, ArrayList<String> args) {
        int argOneCmdIndex = 0;
        String string = "";
        
        for (int i = 0; i < commands.size(); i++) {
            if (args.isEmpty()) {
                string += "`" + commands.get(i).getPrefix() + commands.get(i).getCommand() + "` - " + commands.get(i).getDescription() + "\n";

            } else if (commands.get(i).getCommand().equals(args.get(0))) {
                argOneCmdIndex = i;
                break;
            }

        }

        if (argOneCmdIndex == 0) {
            isList(true);
            return string;
        } else {
            isList(false);
            string = "`" + commands.get(argOneCmdIndex).getUsage() + "`";
            return string;
        }
    }

    private void isList(boolean b) {
        isList = b;
    }
}
