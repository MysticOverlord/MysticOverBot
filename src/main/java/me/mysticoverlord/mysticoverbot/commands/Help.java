/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.commands;

import java.util.List;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.mysticoverlord.mysticoverbot.CommandManager;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.ICommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Help
implements ICommand {
    private CommandManager manager;

    public Help(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        if (args.isEmpty()) {
            DefaultEmbed(event);
            return;
        }
        
        switch (args.get(0)) {
        case "fun":
        	FunEmbed(event);
        	break;
        case "util":
        	UtilEmbed(event);
        	break;
        case "music":
        	MusicEmbed(event);
        	break;
        case "mod":
        	ModEmbed(event);
        	break;
        case "interactive":
        	InteractiveEmbed(event);
        	break;
        case "memes":
        	MemeEmbed(event);
        	break;
        case "giveaway":
        	GiveawayEmbed(event);
        	break;
        default: 
        	ICommand command = manager.getCommand(String.join((CharSequence)"", args));
            if (command == null) {
                event.getChannel().sendMessage("Dude...").queue();
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage("What are you talking about?").queue();
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage("Read the command list before asking for details mate!").queue();
                return;
            }
            String message = "Usage for " + command.getInvoke() + "\n" + command.getUsage() + "\n" + command.getHelp();
            event.getChannel().sendMessage(message).queue();
        }  
    }
    
    void UtilEmbed(GuildMessageReceivedEvent event) {
        EmbedBuilder builder = EmbedUtils.getDefaultEmbed()
        		.setTitle("Utility Commands")
        		.addField("Ping", "Shows you how long I take to respond.\nUsage: " + Constants.PREFIX + "ping", false)
        		.addField("Bot Info", "Gives you bot info, Server link, Bot invite and the link to my patreon page.\nUsage: " + Constants.PREFIX + "botinfo", false)
        		.addField("Invite", "Displays bot and server invite along with the patreon page.\nUsage: " + Constants.PREFIX + "invite", false)
        		.addField("ServerInfo", "Gives you all the numbers and data of your server.\nUsage: " + Constants.PREFIX + "serverinfo", false)
        		.addField("Userinfo", "Get the userinfo of a specific guild member.\nUsage: " + Constants.PREFIX + "uinfo ``<@User>``", false)
        		.addField("Update", "Gives you the latest changes.\nUsage: " + Constants.PREFIX + "update", false)
        		.addField("Suggestions", "Suggest new Features to the hideout.\nUsage: " + Constants.PREFIX + "suggest ``<suggestion>``", false)
        		.addField("Embed", "Embeds your message.\nUsage: " + Constants.PREFIX + "embed ``<message>``", false)
        		.addField("Avatar", "Get user avatar.\nUsage: " + Constants.PREFIX + "avatar ``<@User>``", false).setFooter("Issued by " + event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl());
        event.getChannel().sendMessage(builder.build()).queue();
    }

    void FunEmbed(GuildMessageReceivedEvent event) {
        EmbedBuilder builder = EmbedUtils.getDefaultEmbed()
        		.setTitle("Fun Commands")
        		.addField("8ball", "Ask me a question and i'll answer.\nUsage: " + Constants.PREFIX + "8ball ``<question>``", false)
        		.addField("Google", "Give me something to google.\nUsage: " + Constants.PREFIX + "google ``<search>``", false)
        		.addField("Dice", "Throw 2 dice for a number of 2-12.\nUsage: " + Constants.PREFIX + "dice", false)
        		.addField("Cat", "Gives you some cute kitties to look at.\nUsage: " + Constants.PREFIX + "cat", false)
        		.addField("Dog", "Releases the hounds.\nUsage: " + Constants.PREFIX + "dog", false)
        		.addField("Joke", "I'll tell you a joke from reddit.\nUsage: " + Constants.PREFIX + "joke", false)
        		.addField("ToBinary", "Converts a number to binary\nUsage: " + Constants.PREFIX + "tobinary ``<number>``", false)
        		.addField("Fortune Cookie", "Get a fortune cookie.\nUsage: " + Constants.PREFIX + "fortune", false)
        	    .addField("Random Fact", "Get any random *likely useless* fact.\nUsage: " + Constants.PREFIX + "randomfact", false)
        	    .addField("Daily Fact", "Get the fact of the day which is also *useless*.\nUsage: " + Constants.PREFIX + "dailyfact", false)
        		.setFooter("Issued by " + event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl());
        event.getChannel().sendMessage(builder.build()).queue();
    }

    void MusicEmbed(GuildMessageReceivedEvent event) {
        EmbedBuilder builder = EmbedUtils.getDefaultEmbed().setTitle("Music Commands")
        		.addField("Join", "I'll join your voice channel.\nUsage: " + Constants.PREFIX + "join", false)
        		.addField("Disconnect", "I'll leave the current voice channel.\nUsage: " + Constants.PREFIX + "disconnect", false)
        		.addField("Play", "Searches and plays the given song from youtube.\nUsage: " + Constants.PREFIX + "play ``<query>``", false)
        		.addField("Clear", "Clears the queue.\nUsage: " + Constants.PREFIX + "clear", false)
        		.addField("Pause", "Pauses/Resumes the queue playback.\nUsage: " + Constants.PREFIX + "pause", false)
        		.addField("Queue", "Displays the queue.\nUsage: " + Constants.PREFIX + "queue", false)
        		.addField("Skip", "Skips the current song.\nUsage: " + Constants.PREFIX + "skip", false)
        		.addField("Now Playing", "Displays the song which is currently playing.\nUsage: " + Constants.PREFIX + "np", false)
        		.addField("Remove", "Removes the given song from the queue.\nUsage: " + Constants.PREFIX + "remove ``<number in queue>``", false)
        		.addField("Replay", "Resets the song playback to 00:00.\nUsage: " + Constants.PREFIX + "replay", false).setFooter("Issued by " + event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl())
        		.addField("Shuffle", "Shuffles the current queue.\nUsage: " + Constants.PREFIX + "shuffle", false)
        		.addField("Loop", "Loops the current song.\nUsage: " + Constants.PREFIX + "loop", false)
        		.addField("Loopqueue", "Loops the queue.\nUsage: " + Constants.PREFIX + "loopqueue", false);
        event.getChannel().sendMessage(builder.build()).queue();
    }

    void ModEmbed(GuildMessageReceivedEvent event) {
        EmbedBuilder builder = EmbedUtils.getDefaultEmbed().setTitle("Mod Commands")
        		.addField("Kick", "Kicks the specified user.\nUsage: " + Constants.PREFIX + "kick ``<@user>``", false)
        		.addField("Ban", "Bans the specified user.\nUsage: " + Constants.PREFIX + "ban ``<@user>``", false)
        		.addField("Unban", "Unbans the specified user or userid.\nUsage: " + Constants.PREFIX + "unban ``<@User/userid>``", false)
        		.addField("Prefix", "Sets a custom prefix for your server.\nUsage: " + Constants.PREFIX + "prefix ``<prefix>``", false)
        		.addField("Prune", "Clears the specified amount of messages.\nUsage: " + Constants.PREFIX + "prune ``<ammount of messages>``", false)
        		.addField("Report Bug", "Reports a bug to the hideout.\nUsage: " + Constants.PREFIX + "reportbug ``<report>``", false)
        		.addField("MuteSetup", "Sets up a \"Muted\" role.\nUsage: " + Constants.PREFIX + "mutesetup", false)
        		.addField("Mute", "Mutes the mentioned user.\nUsage: " + Constants.PREFIX + "mute ``<@user>``", false)
        		.addField("Tempmute", "Mutes the mentiones user for the specified amount of time.\nUsage: " + Constants.PREFIX + "tempmute <@user> <d# h# m#>", false)
        		.addField("Nickname", "Assigns a new nickname to the mentioned user.\nUsage: " + Constants.PREFIX + "nick ``<@user> <nickname>``", false)
        		.addField("NickLog", "Makes log entries for all nickname changes.\nUsage: " + Constants.PREFIX + "nicklog ``[enable/disable]``", false)
        		.addField("AntiInvite", "Activates invite preventio.\nUsage: " + Constants.PREFIX + "antiinvite ``[enable/disable]``", false)
        		.addField("Swear Filter", "Activates the swear filter.\nUsage: " + Constants.PREFIX + "swearfilter ``[enable/disable]``", false)
        		.addField("Message Log", "Logs deleted and edited messages.\nUsage: " + Constants.PREFIX + "log ``[enable/disable]``", false)
        		.addField("Warn", "Warns the specified user.\nUsage: " + Constants.PREFIX + "warn ``<@user> <reason>", false)
        		.addField("Warnings", "Views, clears or sets someones warning count.\nUsage: " + Constants.PREFIX + "warnings <@user> [clear,set,show]", false)
        		.setFooter("Issued by " + event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl());
        event.getChannel().sendMessage(builder.build()).queue();
    }

    void InteractiveEmbed(GuildMessageReceivedEvent event) {
        EmbedBuilder builder = EmbedUtils.getDefaultEmbed().setTitle("Interactive Commands")
        		.addField("Battle", "Have a fight to the death with another member.\nUsage: " + Constants.PREFIX + "battle ``[@User1] [@User2]``", false)
        		.addField("Choose", "Give me any amount of objects and I will choose one.\nUsage: " + Constants.PREFIX + "choose ``<object1> <or> <object2> [or] [object3] etc...``", false)
        		.addField("Cry", "Show how sad you feel.\nUsage: " + Constants.PREFIX + "cry", false)
        		.addField("Facepalm", "Show your disappointment.\nUsage: " + Constants.PREFIX + "facepalm", false)
        		.addField("Punch", "Punch anybody.\nUsage: " + Constants.PREFIX + "punch ``<@User>``", false)
        		.addField("Run", "Run away! Run away!\nUsage: " + Constants.PREFIX + "run", false)
        		.addField("Shoot", "Shoot somebody!\nUsage: " + Constants.PREFIX + "shoot ``<@User>``", false)
        		.addField("Smile", "Because you're happy!\nUsage: " + Constants.PREFIX + "smile", false)
        		.addField("Loveship", "See if it works out with the one you love!\nUsage: " + Constants.PREFIX + "loveship ``<@User1> [@User2]``", false)
        		.addField("Say", "You be the human, I be the parrot\nUsage: " + Constants.PREFIX + "say ``sentence``", false)
        		.setFooter("Issued by " + event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl());
        event.getChannel().sendMessage(builder.build()).queue();
        
    }
    
    void MemeEmbed(GuildMessageReceivedEvent event) {
    	EmbedBuilder builder = EmbedUtils.getDefaultEmbed()
    			.setTitle("Meme Commands")
    			.setDescription("To get any meme just use " + Constants.PREFIX + "meme."
    					+ "\nOr if you want memes from specific subreddits you can use one of the commands listed below:\n\n"
    					+ Constants.PREFIX + "adviceanimal -> r/AdviceAnimals\n"
    					+ Constants.PREFIX + "assholedesign -> r/assholedesign\n"
    					+ Constants.PREFIX + "comedyheaven -> r/comedyheaven\n"
    					+ Constants.PREFIX + "crappydesign -> r/CrappyDesign\n"
    					+ Constants.PREFIX + "dankmeme -> r/dankmemes\n"
    					+ Constants.PREFIX + "deepfriedmeme -> r/DeepFriedMemes\n" 
    					+ Constants.PREFIX + "funny -> r/funny\n"
    					+ Constants.PREFIX + "gatekeeping -> r/gatekeeping\n"
    					+ Constants.PREFIX +  "historymeme -> r/HistoryMemes\n"
    					+ Constants.PREFIX + "ihadastroke -> r/ihadastroke\n"
    					+ Constants.PREFIX + "ihavereddit -> r/ihavereddit\n"
    					+ Constants.PREFIX +  "meirl -> r/meirl\n"
    					+ Constants.PREFIX +  "memeeconomy -> r/MemeEconomy\n"
    					+ Constants.PREFIX + "mildlyinfuriating -> r/mildlyinfuriating\n"
    					+ Constants.PREFIX + "onejob -> r/OneJob\n"
    					+ Constants.PREFIX + "rareinsult -> r/rareinsults\n"
    					+ Constants.PREFIX + "wholesomememe -> r/wholesomememes\n"
    				    + Constants.PREFIX + "comedycemetery -> r/ComedyCemetery\n"
    				    		+ Constants.PREFIX + "discordmeme -> r/Discordmemes\n"
    				    				+ Constants.PREFIX + "blursedimage -> r/BlursedImages");
        event.getChannel().sendMessage(builder.build()).queue();
    }
    
    void GiveawayEmbed(GuildMessageReceivedEvent event) {
    	 EmbedBuilder builder = EmbedUtils.getDefaultEmbed().setTitle("Giveaway Commands")
         		.addField("Start", "Starts a new Giveaway.\nUsage: " + Constants.PREFIX + "gstart `<#channel/channelID> <d# h# m#>` `<prize | amount>`", false)
         		.addField("Reroll", "Selects a new Winner for an ended Giveaway.\nUsage: " + Constants.PREFIX + "greroll `<channelId>` `<messageId>`", false)
         		.addField("End", "Ends a Giveaway and selects winners.\nUsage: " + Constants.PREFIX + "gend `<messageId>`", false)
         		.addField("Cancel", "Cancels a Giveaway\nUsage: " + Constants.PREFIX + "gcancel `<messageId>`", false)
         		.setFooter("Issued by " + event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl());
         event.getChannel().sendMessage(builder.build()).queue();
    }

    void DefaultEmbed(GuildMessageReceivedEvent event) {
        EmbedBuilder builder = EmbedUtils.getDefaultEmbed()
        		.setTitle("Bot Info")
        		.setThumbnail(event.getJDA().getSelfUser().getEffectiveAvatarUrl().toString())
        		.setDescription("Welcome to MysticOverBot!\n\nThis command is categorized in the following sub-commands:\n\n" + 
        		Constants.PREFIX + "``help util``: gives you the Utility Commands\n\n" + 
        				Constants.PREFIX + "``help fun``: gives you the Fun Commands\n\n" + 
        		Constants.PREFIX + "``help music``: gives you the Music Commands  \n\n" + 
        				Constants.PREFIX + "``help mod``: gives you the Mod Commands\n\n" + 
        		Constants.PREFIX + "``help interactive``: give you the Interactive Commands\n\n" + 
        				Constants.PREFIX + "``help memes``: gives you the Meme Commands.\n\n" +
        		Constants.PREFIX + "``help giveaway``: gives you the Giveaway Commands.")//.addField("Extra Info", "This bot is running on limited capacity. Timeouts areexpected!", false)
        		.addField("Version", Constants.VERSION, false)
        		.setFooter("Have Fun using me!");
                
       try {
        builder.addField("Credits", event.getJDA().getUserById(508720470275653636L).getAsTag() + ": Server Icon Design", true);
       } catch (NullPointerException e) {
    	   builder.addField("Credits", "♡Beth♡#1213: Server Icon Design", true);
       }
       
       try {
       if (event.getGuild().getMembers().contains(event.getGuild().getMember(event.getJDA().getUserById(Constants.OWNER)))) {
    	   builder.addField("Owner", event.getJDA().getUserById(394066807926947850L).getAsMention(), true);
       } else { 
    	   builder.addField("Owner", event.getJDA().getUserById(394066807926947850L).getAsTag(), true);
       }
       } catch (Exception e) {
    	   e.printStackTrace();
    	   builder.addField("Owner", event.getJDA().getUserById(394066807926947850L).getAsTag(), true);
       }
        		
        		builder.setFooter("Issued by " + event.getAuthor().getAsTag(), event.getAuthor()
        				.getAvatarUrl());
        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public String getHelp() {
        return "Shows this Message";
    }

    @Override
    public String getInvoke() {
        return "help";
    }

    @Override
    public String getUsage() {
        return "Usage: " + Constants.PREFIX + getInvoke() + " ``<command>`` *optional*";
    }
}

