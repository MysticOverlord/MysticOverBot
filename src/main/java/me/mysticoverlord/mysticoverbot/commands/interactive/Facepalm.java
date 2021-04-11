/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.commands.interactive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.FilterUtil;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Facepalm
implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        String author = event.getAuthor().getName();
        ArrayList<String> mentioned = new ArrayList<String>();
    	try {
    	mentioned.add(FilterUtil.getMemberByQuery(String.join(" ", args), event, 0).getUser().getName());
    	} catch (NullPointerException e) {
    	}
        Random r = new Random();
        ArrayList<String> facepalm = new ArrayList<String>();
        facepalm.add("https://media1.tenor.com/images/06655070b3cc8faaff4824eee848adc0/tenor.gif?itemid=5436796");
        facepalm.add("https://media1.tenor.com/images/fa7be96565d6a62208b61497b92576b7/tenor.gif?itemid=9140688");
        facepalm.add("https://media.tenor.com/images/8effa7b7964c33cf80d21e73cdf44359/tenor.gif");
        facepalm.add("https://media.tenor.com/images/5da7d0fa3dff6ab5fde7ef190b994b0a/tenor.gif");
        facepalm.add("https://media.tenor.com/images/dbd46593787e35356e3853ad0c13657c/tenor.gif");
        facepalm.add("https://media.tenor.com/images/0637eb9bbe49a34ac38061f3c6e3632d/tenor.gif");
        facepalm.add("https://media.tenor.com/images/17921e76108b69a71dae510d954bd7d5/tenor.gif");
        facepalm.add("https://media.tenor.com/images/331fdc0e4bbc7514ef8571296d54979b/tenor.gif");
        facepalm.add("https://media.tenor.com/images/15adb8ae5cf59ebcfe673bd84b58150f/tenor.gif");
        facepalm.add("https://media.tenor.com/images/781910769fa86b47917c936969cec979/tenor.gif");
        facepalm.add("https://media1.tenor.com/images/0f78af841f453545a036b6cceb3620cc/tenor.gif?itemid=4780258");
        facepalm.add("https://media1.tenor.com/images/6b3eabb31c74d63787c178d980f1bde3/tenor.gif?itemid=5727928");
        facepalm.add("https://media1.tenor.com/images/4f24982830d0abe2000851d76b86de64/tenor.gif?itemid=7929295");
        facepalm.add("https://media1.tenor.com/images/c93698e1b19f97cee7fc87d7a5758372/tenor.gif?itemid=5723399");
        facepalm.add("https://media1.tenor.com/images/4ee73851c3bfa93e45b5cbdb5fc42900/tenor.gif?itemid=12001213");
        int random = r.nextInt(facepalm.size());
        MessageBuilder message = new MessageBuilder();
        EmbedBuilder embed = EmbedUtils.getDefaultEmbed();
        embed.setImage((String)facepalm.get(random)).setTitle(String.valueOf(author) + " has facepalmed").setFooter("requested by " + author, event.getAuthor().getEffectiveAvatarUrl());
        message.setEmbed(embed.build());
        channel.sendMessage(embed.build()).queue();
    }

    @Override
    public String getHelp() {
        return "facepalms";
    }

    @Override
    public String getInvoke() {
        return "facepalm";
    }

    @Override
    public String getUsage() {
        return "Usage: " + Constants.PREFIX + this.getInvoke();
    }
}

