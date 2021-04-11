/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.commands.interactive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.ICommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Run
implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        String author = event.getAuthor().getName();
        Random r = new Random();
        ArrayList<String> run = new ArrayList<String>();
        run.add("https://media1.giphy.com/media/e1Lv6Gvd8bFFC/source.gif");
        run.add("https://media1.giphy.com/media/Ojr9vupbS0v0GWDgxm/giphy.gif");
        run.add("https://media0.giphy.com/media/l0MZ7g6YUsd60aYAE/giphy.gif");
        run.add("https://media1.giphy.com/media/3o85xo92HdITupBxW8/source.gif");
        run.add("https://media3.giphy.com/media/xTiTnoVw7Iyq6lcKn6/giphy.gif");
        run.add("https://media0.giphy.com/media/oNTTA0N4GvZegMfRw9/giphy.gif");
        run.add("https://i.pinimg.com/originals/09/ee/e0/09eee0f5dfae8f74179d1ba0bb54b22d.gif");
        run.add("https://media1.giphy.com/media/Zxzr2pp6qU64g/giphy.gif?cid=790b76115b4b6428b8feeed965a663befd0e81035b662448&amp;rid=giphy.gif");
        run.add("http://giphygifs.s3.amazonaws.com/media/OAuf5mQLixvOM/giphy_s.gif");
        run.add("https://media1.tenor.com/images/c5ee4855dca3c6e0afb0f5a0c7d2eb06/tenor.gif?itemid=5609172");
        run.add("https://media1.tenor.com/images/e60597fb34ec71d8dcc66a79fdd4e74c/tenor.gif?itemid=7999726");
        run.add("https://media1.tenor.com/images/08107dec2b3fbb9b2b2abeb1e98d940b/tenor.gif?itemid=13005168");
        run.add("https://media1.tenor.com/images/9532ad11adc3edd368be73acafaa3c51/tenor.gif?itemid=14714578");
        run.add("https://media1.tenor.com/images/597940f4ccc69439001209b12c2d867e/tenor.gif?itemid=9729848");
        run.add("https://media1.tenor.com/images/b1b7844ebf3fca58ee43727a18d61399/tenor.gif?itemid=7176837");

        int random = r.nextInt(run.size());
        MessageBuilder message = new MessageBuilder();
        EmbedBuilder embed = EmbedUtils.getDefaultEmbed();
        embed.setImage((String)run.get(random)).setTitle(String.valueOf(author) + " just ran away!").setFooter("requested by " + author, event.getAuthor().getEffectiveAvatarUrl());
        message.setEmbed(embed.build());
        channel.sendMessage(embed.build()).queue();
    }

    @Override
    public String getHelp() {
        return "when they approach\nYou run away!";
    }

    @Override
    public String getInvoke() {
        return "run";
    }

    @Override
    public String getUsage() {
        return "Usage: " + Constants.PREFIX + this.getInvoke();
    }
}

