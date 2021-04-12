/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.commands.owner;

import java.util.List;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Reply
implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        String input = event.getMessage().getContentRaw();
        if (event.getAuthor().getIdLong() != 394066807926947850L) {
            return;
        }
        User user = event.getJDA().getUserById(args.get(0));
        String message = input.replace(String.valueOf(Constants.PREFIX) + this.getInvoke(), "");
        message = message.replace(args.get(0), "");
        MessageEmbed builder = EmbedUtils.getDefaultEmbed().setTitle("Owner Reply").setDescription(message).setFooter("Sent by " + event.getAuthor().getAsTag(), event.getAuthor().getEffectiveAvatarUrl()).build();
        event.getChannel().sendMessage("reply sent").queue();
        user.openPrivateChannel().queue(channel -> channel.sendMessage(builder).queue());
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public String getInvoke() {
        return "reply";
    }

    @Override
    public String getUsage() {
        return null;
    }
}

