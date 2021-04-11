/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.commands.fun;

import java.util.List;
import java.util.Random;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Dice
implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        @SuppressWarnings("unused")
		int d2;
        Random r = new Random();
        String word = "a";
        int d1 = r.nextInt(6) + 1;
        int result = d1 + (d2 = r.nextInt(6) + 1);
        if (result == 8 || result == 11) {
            word = "an";
        }
        event.getChannel().sendMessage("You threw the dice!").queue();
        event.getChannel().sendMessage("You have thrown " + word + " " + result).queue();
    }

    @Override
    public String getHelp() {
        return "Throws the dice";
    }

    @Override
    public String getInvoke() {
        return "dice";
    }

    @Override
    public String getUsage() {
        return "Usage: " + Constants.PREFIX + this.getInvoke();
    }
}

