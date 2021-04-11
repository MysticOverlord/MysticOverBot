/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.commands.fun;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import me.duncte123.botcommons.web.WebUtils;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Joke
implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        WebUtils.ins.getJSONObject("https://apis.duncte123.me/joke?NSFW=FALSE").async(json -> {
            JsonNode data = json.get("data");
            event.getChannel().sendMessage(String.valueOf(data.get("title").asText()) + "\n").queue();
            event.getChannel().sendMessage(data.get("body").asText()).queue();
        });
    }

    @Override
    public String getHelp() {
        return "I'll crack a joke";
    }

    @Override
    public String getInvoke() {
        return "joke";
    }

    @Override
    public String getUsage() {
        return "Usage: " + Constants.PREFIX + this.getInvoke();
    }
}

