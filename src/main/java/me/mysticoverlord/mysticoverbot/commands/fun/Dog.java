/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.commands.fun;

import java.util.List;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.utils.AttachmentOption;

public class Dog
implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        WebUtils.ins.getJSONObject("https://random.dog/woof.json").async(json -> {
            String url = json.get("url").asText();
            MessageEmbed embed = EmbedUtils.embedImage(url).setFooter("Issued by " + event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl()).build();
            event.getChannel().sendMessage(embed).queue();
         
        });
    }

    @Override
    public String getHelp() {
        return "Releases the Hounds";
    }

    @Override
    public String getInvoke() {
        return "dog";
    }

    @Override
    public String getUsage() {
        return "Usage: " + Constants.PREFIX + this.getInvoke();
    }
}

