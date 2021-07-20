package io.github.spikey84.botproject.listeners;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;
import okhttp3.internal.http2.Http2Connection;

import java.util.List;
import java.util.function.Consumer;

public class ClearChannelCommand extends ListenerAdapter {

    public ClearChannelCommand() {
        System.out.println("EVENT READYe");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getMessage().getContentRaw().toLowerCase().contains("!clearchannel")) return;

        if (!event.getMember().getPermissions().contains(Permission.ADMINISTRATOR)) {
            event.getMessage().reply("You do not have permission to run this command.").mentionRepliedUser(false)
                    .queue();
            return;
        }

        event.getMessage().reply("Are you sure u want to delete all messages?").mentionRepliedUser(false)
                .setActionRow(net.dv8tion.jda.api.interactions.components.Button.success("confirm", "confirm"),
                        Button.danger("deny", "deny"))
                .queue();
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        if(!event.getMessage().getContentRaw().equals("Are you sure u want to delete all messages?")) return;

        if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            event.deferEdit();
            return;
        }

        if (event.getComponentId().equals("confirm")) {
            event.getMessage().getTextChannel().getIterableHistory().queue( messageHistory -> {
                Message message = messageHistory.get(0);
                int x = 1;
                message.delete().queue((result) -> {

                   reDel(x, messageHistory);
                    System.out.println("del");
                });

            });



        } else if (event.getComponentId().equals("deny")) {
            if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                event.deferEdit();
                event.getMessage().delete().queue();
                return;
            }
        }
    }

    public int reDel(int i, List<Message> mesHis) {
        if (i == -1) return -1;
        if (mesHis.size() < i) return -1;
        mesHis.get(i).delete().queue((result) -> {
            reDel(i+1, mesHis);
            System.out.println("del");
        });
        return -1;
    }
}
