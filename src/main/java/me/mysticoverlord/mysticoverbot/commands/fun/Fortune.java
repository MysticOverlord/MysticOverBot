/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.commands.fun;

import java.util.List;
import java.util.concurrent.TimeUnit;
import me.duncte123.botcommons.web.WebUtils;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Fortune
implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        WebUtils.ins.getJSONObject("http://yerkee.com/api/fortune").async(json -> {
            String fortune = json.get("fortune").asText();
            event.getChannel().sendMessage("You have eaten a fortune cookie :cookie:").queue();
            event.getChannel().sendMessage("Look! There is a message inside!").queueAfter(1L, TimeUnit.SECONDS);
            event.getChannel().sendMessage("It says:\n" + fortune).queueAfter(2L, TimeUnit.SECONDS);
        });
    }

    @Override
    public String getHelp() {
        return "Have a fortune cookie!";
    }

    @Override
    public String getInvoke() {
        return "fortune";
    }

    @Override
    public String getUsage() {
        return "Usage: " + Constants.PREFIX + this.getInvoke();
    }
}

