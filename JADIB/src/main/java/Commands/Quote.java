package commands;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.javacord.api.entity.message.embed.EmbedBuilder;

import commandhandler.Command;
import commandhandler.CommandData;
import commandhandler.CommandExecutor;
import commands.util.JADIBUtil;

public class Quote implements CommandExecutor {

    @Override
    public void execute(CommandData data, ArrayList<Command> commands) throws IOException {
        // TODO Auto-generated method stub

        String input = getTextOnPage();

        String quote = getQuote(input);
        String author = getAuthor(input);

        sendMessage(data, quote, author);

        //

    }

    private void sendMessage(CommandData data, String quote, String author) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Insperational quote");
        embed.setColor(JADIBUtil.color);
        embed.setTimestampToNow();
        embed.addField(quote, author, false);

        data.getChannel().sendMessage(embed);
    }

    private String getAuthor(String input) {
        int begginingIndex = input.indexOf("a\":\"") + 4;
        int endingIndex = input.indexOf("\",\"h");

        String author = input.substring(begginingIndex, endingIndex);

        return author;
    }

    private String getQuote(String input) {
        int begginingIndex = input.indexOf("\"", 6);
        int endingIndex = input.indexOf("\",\"");

        String quote = input.substring(begginingIndex, endingIndex + 1);

        return quote;
    }

    private String getTextOnPage() throws IOException {

        URL url = new URL("https://zenquotes.io/api/random");

        Scanner sc = new Scanner(url.openStream());

        String result = "";

        while (sc.hasNextLine()) {
            result = sc.nextLine();
        }

        return result;
    }
}
