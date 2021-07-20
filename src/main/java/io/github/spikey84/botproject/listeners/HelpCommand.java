package io.github.spikey84.botproject.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;

import java.awt.*;

public class HelpCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getMessage().getContentRaw().toLowerCase().contains("!commands")) return;

        MessageEmbed messageEmbed = new EmbedBuilder().setDescription("Commands:")
                .addField(new MessageEmbed.Field("!ClearChannel", "Deletes all messages sent in a channel.", false))
                .addField(new MessageEmbed.Field("!help", "Displays this menu", false))
                .addField(new MessageEmbed.Field("!ping", "Pong!", false))
                .setColor(Color.PINK)
                .setFooter("Contact Spikey Noob#8464 with any issues!")
                .build();

        Message message = new MessageBuilder().setEmbeds(messageEmbed)
                .build();



        event.getMessage().reply(message).queue();
    }
}
