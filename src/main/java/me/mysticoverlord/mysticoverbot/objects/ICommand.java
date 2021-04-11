/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.objects;

import java.util.List;

import me.mysticoverlord.mysticoverbot.Constants;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface ICommand {
    public void handle(List<String> args, GuildMessageReceivedEvent event) throws Exception;

    public String getHelp();

    public String getInvoke();

    public String getUsage();
}

