package threads;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.activity.ActivityType;

import src.Main;
import utility.JADIBUtil;

public class StatusLoopThread extends Thread{
    public void run() {
        DiscordApi api = Main.getApi();
        do {
            int choice = (int) Math.round(Math.random() * 3 + 1);
            switch (choice) {
            case 1: {
                api.updateActivity(ActivityType.WATCHING, "over " + api.getServers().size() + " servers!");
                break;
            }
            case 2: {
                api.updateActivity(ActivityType.WATCHING, "Netflix and chill.");
                break;
            }
            case 3: {
                api.updateActivity(ActivityType.PLAYING, "ungrills your cheese.");
                break;
            }
            case 4: {
                api.updateActivity(ActivityType.PLAYING, "It's pronounced jah-dib.");
                break;
            }
            case 5: {
                api.updateActivity(ActivityType.WATCHING, "for " + JADIBUtil.prefix);
                break;
            }
            }
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } // 5 minutes
        } while (true);
    }
}
