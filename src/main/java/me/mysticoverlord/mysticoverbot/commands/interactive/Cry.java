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
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Cry
implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        String author = event.getAuthor().getName();
        ArrayList<String> mentioned = new ArrayList<String>();
        if (event.getMessage().getMentions(new Message.MentionType[0]).size() >= 1) {
            mentioned.add(((User)event.getMessage().getMentions(new Message.MentionType[0]).get(0)).getName().toString());
        }
        Random r = new Random();
        ArrayList<String> cry = new ArrayList<String>();
        cry.add("https://media3.giphy.com/media/d2lcHJTG5Tscg/giphy.gif?cid=790b76113c463bea7968df0d6f95eb84807d39e8be0bff22&amp;rid=giphy.gif");
        cry.add("https://media1.giphy.com/media/AauJT0w8cJoSQ/giphy.gif?cid=790b76113f01625814f8353d0eed518365a55ca4a236faa9&amp;rid=giphy.gif");
        cry.add("https://media2.giphy.com/media/2WxWfiavndgcM/giphy.gif?cid=790b76113c463bea7968df0d6f95eb84807d39e8be0bff22&amp;rid=giphy.gif");
        cry.add("https://media2.giphy.com/media/l0HlDJhyI8qoh7Wfu/giphy.gif?cid=790b76116d201222129a4758bc73b133382727f6060b3953&amp;rid=giphy.gif");
        cry.add("https://media2.giphy.com/media/l0HlDJhyI8qoh7Wfu/giphy.gif?cid=790b76116d201222129a4758bc73b133382727f6060b3953&amp;rid=giphy.gif");
        cry.add("https://media2.giphy.com/media/TW8Ma1a8ZsZ8I/giphy.gif?cid=790b761180d864df01927f41194df7a8bcfa19a771962188&amp;rid=giphy.gif");
        cry.add("https://media.giphy.com/media/131s7DE2m4UWwo/giphy.gif");
        cry.add("https://media3.giphy.com/media/aWQ4xGF8dmupq/giphy.gif?cid=790b76112055af609562e4ab61e4f3c913462fa25a28f666&amp;rid=giphy.gif");
        cry.add("https://media3.giphy.com/media/BeL3iFbYzAsfu/giphy.gif?cid=790b7611866943f8788ff11c363fa80b361775b9a5908c61&amp;rid=giphy.gif");
        cry.add("https://media3.giphy.com/media/hOCYRFpBlzoyc/giphy.gif?cid=790b7611272e04088d70e439dd1db5ebaa58b08a6169018c&amp;rid=giphy.gif");
        int random = r.nextInt(cry.size());
        MessageBuilder message = new MessageBuilder();
        EmbedBuilder embed = EmbedUtils.getDefaultEmbed();
        embed.setImage((String)cry.get(random)).setTitle(String.valueOf(author) + " is crying").setFooter("requested by " + author, event.getAuthor().getEffectiveAvatarUrl());
        message.setEmbed(embed.build());
        channel.sendMessage(embed.build()).queue();
    }

    @Override
    public String getHelp() {
        return "Cries";
    }

    @Override
    public String getInvoke() {
        return "cry";
    }

    @Override
    public String getUsage() {
        return "Usage: " + Constants.PREFIX + this.getInvoke();
    }
}

