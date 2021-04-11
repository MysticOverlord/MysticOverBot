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

public class Smile
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
        ArrayList<String> smile = new ArrayList<String>();
        smile.add("https://media2.giphy.com/media/xSM46ernAUN3y/giphy.gif?cid=790b76114cdd6e981c0fed52502f2239960b979c993171c5&amp;rid=giphy.gif");
        smile.add("https://media1.giphy.com/media/Mp4hQy51LjY6A/giphy.gif?cid=790b76114cdd6e981c0fed52502f2239960b979c993171c5&amp;rid=giphy.gif");
        smile.add("https://media2.giphy.com/media/l2JJO0D0JpgoU5OTe/giphy.gif?cid=790b76114cdd6e981c0fed52502f2239960b979c993171c5&amp;rid=giphy.gif");
        smile.add("https://media0.giphy.com/media/1mtKnWJVTpUKQ/giphy.gif?cid=790b7611de1e619ae26f090e1566cafb660db5db5ad4a511&amp;rid=giphy.gif");
        smile.add("https://media0.giphy.com/media/rIq6ASPIqo2k0/giphy.gif?cid=790b7611658477fbb36ae1c45cb8ebf6ee992888e4be16d4&amp;rid=giphy.gif");
        smile.add("https://media3.giphy.com/media/P8MxmGnjmytws/giphy.gif?cid=790b7611658477fbb36ae1c45cb8ebf6ee992888e4be16d4&amp;rid=giphy.gif");
        smile.add("https://media2.giphy.com/media/cXmUgAGDMgomI/giphy.gif?cid=790b76114e5b81ac46b4d0ab666df40ddc663f7fe167d4c2&amp;rid=giphy.gif");
        smile.add("https://media1.giphy.com/media/xT5LMsYxfqniWvh47m/giphy.gif?cid=790b76112942abda6d0742ccacc88af44af39c2174bd7518&amp;rid=giphy.gif");
        smile.add("https://media1.giphy.com/media/KIylr696mVUkM/giphy.gif?cid=790b76111c70ecc2db8bcd772cd969a185910402dc70c7dd&amp;rid=giphy.gif");
        int random = r.nextInt(smile.size());
        MessageBuilder message = new MessageBuilder();
        EmbedBuilder embed = EmbedUtils.getDefaultEmbed();
        embed.setImage((String)smile.get(random)).setTitle(String.valueOf(author) + " is smiling").setFooter("requested by " + author, event.getAuthor().getEffectiveAvatarUrl());
        message.setEmbed(embed.build());
        channel.sendMessage(embed.build()).queue();
    }

    @Override
    public String getHelp() {
        return "smiles";
    }

    @Override
    public String getInvoke() {
        return "smile";
    }

    @Override
    public String getUsage() {
        return "Usage: " + Constants.PREFIX + this.getInvoke();
    }
}

