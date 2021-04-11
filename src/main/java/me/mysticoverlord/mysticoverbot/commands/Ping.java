/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.commands;

import java.util.List;
import java.util.concurrent.TimeUnit;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Ping
implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("Pong").queue(message -> {
            message.editMessageFormat("Bot Latency is %s ms.",
            		event.getJDA().getGatewayPing()).queueAfter(2L, TimeUnit.SECONDS);
        });
    }

    @Override
    public String getHelp() {
        return "Shows how long i take to respond to commands!";
    }

    @Override
    public String getInvoke() {
        return "ping";
    }

    @Override
    public String getUsage() {
        return "Usage: " + Constants.PREFIX + getInvoke();
    }
}

