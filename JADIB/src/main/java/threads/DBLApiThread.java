package threads;

import java.util.ArrayList;

import org.discordbots.api.client.DiscordBotListAPI;
import org.javacord.api.entity.user.User;

import src.Main;
import utility.Secret;

public class DBLApiThread extends Thread{
    public void run() {

        DiscordBotListAPI api = new DiscordBotListAPI.Builder()
        .token(Secret.getDblToken())
        .botId("798780702463885322")
        .build();
        
        System.out.println("-- Vote Thread Active --");

        ArrayList<User> users = new ArrayList<User>();
        
        // waits 10 minutes; gives it some time to cache all the members.
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {

            users.clear(); // empty the user list
            
            // goes through all members and checks for votes.
            // should update automatically due to recalling the getCachedUsers
            for (User user : Main.getApi().getCachedUsers()) {
                
                // checks that a user isnt a bot or itself, ie wasting time.
                if (!user.isBot() || !user.isYourself()) {
                    api.hasVoted(user.getIdAsString()).whenComplete((hasVoted, e) -> {
                        
                        if(hasVoted){
                            // TODO add user giving money if also last vote before 12 hours
                            
                        }
                    });
                }

                // waits 5 seconds between each user
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }                
        }
    }
}
