package io.github.spikey84.botproject;

import io.github.spikey84.botproject.listeners.ClearChannelCommand;
import io.github.spikey84.botproject.listeners.HelpCommand;
import io.github.spikey84.botproject.listeners.PingCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    //only static cus main needs to be and i cannot create this in main without it being static


    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a token!");
            System.exit(1);
        }
        String token = args[0];

        JDA jda = null;
        try {
            jda = JDABuilder.createDefault(token)
                    .setActivity(Activity.playing("code code code"))
                    .addEventListeners(new ClearChannelCommand(), new PingCommand(), new HelpCommand())
                    .build();
            System.out.println("Login Success!");
        } catch (LoginException e) {
            System.out.println("Login Failed");
            System.exit(1);
        }

        //jda.upsertCommand("announce", "announces given text").queue();
    }
//    public static void main(String[] args) throws Exception{
//        JDABuilder.createDefault(args[0]) // don't use the deprecated constructor
//                .addEventListeners(new ClearChannelCommand()) // register your listener
//               .build();
//    }

}
