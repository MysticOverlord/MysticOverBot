/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.commands.memes;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Dankmemes
implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
    	
        WebUtils.ins.getJSONArray("https://www.reddit.com/r/dankmemes/random.json").async(json -> {
            JsonNode data = json.get(0).get("data").get("children").get(0).get("data");
            String url = data.get("url").asText();
            MessageEmbed embed = EmbedUtils.embedImage(url)
            		.setTitle(data.get("title").asText(), data.get("url").asText())
            		.setFooter("👍 " + data.get("ups"), event.getAuthor().getAvatarUrl()).build();
            event.getChannel().sendMessage(embed).queue();
        });
    }

    @Override
    public String getHelp() {
        return "Gets ya some dank memes";
    }

    @Override
    public String getInvoke() {
        return "dankmeme";
    }

    @Override
    public String getUsage() {
        return "Usage: " + Constants.PREFIX + this.getInvoke();
    }
}

