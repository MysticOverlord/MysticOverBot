package me.mysticoverlord.mysticoverbot.objects;

import java.awt.Color;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ModUtil {



    /**
     * Iterates over a String input and checks whether a cuss word was found in a list, then checks if the word should be ignored (e.g. bass contains the word *ss).
     * @param input
     * @return
     */
	public static String warningPunishment(int warnings, GuildMessageReceivedEvent event, Member member) {	

    	if (!roleExists(event) == true) {
			try {   			
				event.getGuild().getTextChannelsByName("log", true).get(0).sendMessage(member.getAsMention() + " has had over 3 Automod violations!\nDue to haveing detected no Muted role no Mute Punishments are possible!").queue();			
			} catch (IndexOutOfBoundsException e) {				
				event.getGuild().getTextChannelsByName("logs", true).get(0).sendMessage(member.getAsMention() + " has had over 3 Automod violations!\nDue to haveing detected no Muted role no Mute Punishments are possible!").queue();			
			}
			return null;
		}
		
		Role role = event.getGuild().getRolesByName("Muted", true).get(0);
		
    	switch(warnings) {
    	case 3:
    		event.getGuild().addRoleToMember(member, role).queue();
    		SQLiteUtil.updateMuted(event.getGuild().getId(), member.getId(), FormatUtil.dateCalculator("0", "0", "30"), event);
    		return "30 Minutes";
    	case 4: event.getGuild().addRoleToMember(member, role).queue();
    	        SQLiteUtil.updateMuted(event.getGuild().getId(), member.getId(), FormatUtil.dateCalculator("0", "1", "0"), event);
    	return "1 hour";
    	case 5: event.getGuild().addRoleToMember(member, role).queue();
    	        SQLiteUtil.updateMuted(event.getGuild().getId(), member.getId(), FormatUtil.dateCalculator("0", "6", "0"), event);
    	return "6 hours";
    	case 6: event.getGuild().addRoleToMember(member, role).queue();
    			SQLiteUtil.updateMuted(event.getGuild().getId(), member.getId(), FormatUtil.dateCalculator("1", "0", "0"), event);
    	return "1 day";
    	case 7: event.getGuild().addRoleToMember(member, role).queue();
				SQLiteUtil.updateMuted(event.getGuild().getId(), member.getId(), FormatUtil.dateCalculator("7", "0", "0"), event);
				return "7 days";
    	case 8: event.getGuild().addRoleToMember(member, role).queue();
				SQLiteUtil.updateMuted(event.getGuild().getId(), member.getId(), FormatUtil.dateCalculator("30", "0", "0"), event);
				return "30 days";
    	case 9: member.kick("Automod: 9th Warning").queue();
    	        return "Kicked";
    	case 10: member.ban(0, "Automod: 10th Warning").queue();
    	SQLiteUtil.deleteUserFromModeration(member.getId(), event.getGuild().getId());
    	         return "Banned";
    	default:
    		return null;

    	}
    }
	
    public static boolean roleExists(GuildMessageReceivedEvent event) {
		try {
			event.getGuild().getRolesByName("Muted", true).get(0);
			return true;
		} catch (Exception e) {
			return false;
		}
	
	}
    
    public static boolean isFromThisGuild(GuildMessageReceivedEvent event) {
		List<String> invites = event.getMessage().getInvites();
		List<Invite> inv = event.getGuild().retrieveInvites().complete();
		event.getJDA().getGuildById("573084332634406923").retrieveInvites().complete().forEach((invite) -> {
			try {
			inv.add(invite);
			} catch (UnsupportedOperationException e) {
				
			}
		});
		
		boolean bool = false;
		for (int x = 0; x < invites.size(); x++) {
			for (int y = 0; y < inv.size(); y++) {
				if (inv.get(y).getCode().equals(invites.get(x))) {
					bool = true;
					break;
				}
			}

		if (bool == false) {
			return false;
		}
    }
		
		return true;
    }
    
	public static Color RED = new Color(150, 0, 0);
	public static Color DARK_RED = new Color(100,0,0);
	public static Color YELLOW = new Color(150,150,0);
	public static Color DARK_YELLOW = new Color(150, 100, 0);
	
    public static void doWarn(GuildMessageReceivedEvent event, String reason) {
    	int warnings = 1 + SQLiteUtil.getWarnings(event.getGuild().getId(), event.getAuthor().getId());
        SQLiteUtil.updateWarnings(event.getGuild().getId(), event.getAuthor().getId() , warnings, event);
		String punishment =  ModUtil.warningPunishment(warnings, event, event.getMember());
		if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
			event.getMessage().delete().queue();
		}
		EmbedBuilder builder = EmbedUtils.getDefaultEmbed()
				.addField("User", event.getAuthor().getAsMention(), true)
				.addField("Moderator", event.getJDA().getSelfUser().getAsMention(), true);
		
		if (warnings < 3) {
			builder.setTitle("Warned " + event.getAuthor().getAsTag())
			.setColor(YELLOW).addField("Reason", reason, true);
		} else if (warnings < 9) {
			builder.setTitle("Muted " + event.getAuthor().getAsTag())
			.setColor(DARK_YELLOW).addField("Reason", reason, true )
			.addField("Duration", punishment , true);
		} else if (warnings == 9) {
			builder.setTitle("Kicked " + event.getAuthor().getAsTag())
			.setColor(RED).addField("Reason", reason + " (9th Violation)", true);
		} else if (warnings >= 10) {
			builder.setTitle("Banned " + event.getAuthor().getAsTag())
			.setColor(DARK_RED).addField("Reason", reason + " (" + warnings + "th Violation)", true);
		}
		
		builder.addField("Current Warnings", String.valueOf(warnings), true)
		.addField("Message", event.getMessage().getContentDisplay(), true)
		.addField("In channel", event.getChannel().getAsMention(), true);
		
		try { 
	    	TextChannel channel = event.getGuild().getTextChannelsByName("log", true).get(0);
	    	if (channel != null && event.getGuild().getSelfMember().hasPermission(channel, Permission.MESSAGE_WRITE)) {
	    		channel.sendMessage(builder.build()).timeout(10, TimeUnit.MILLISECONDS).queue();
	    	}
		} catch (IndexOutOfBoundsException e) {
		}
    	if (!event.getGuild().getSelfMember().hasPermission(event.getChannel(), Permission.MESSAGE_WRITE)) {
    		return;
    	}
		builder.clear()
		.setAuthor(event.getAuthor().getAsTag(), event.getAuthor().getEffectiveAvatarUrl(), event.getAuthor().getEffectiveAvatarUrl());


		if (warnings < 3) {
			
			builder.setTitle("Warned " + event.getAuthor().getAsTag())
			.setColor(YELLOW).setDescription(event.getAuthor().getAsMention() + " has been warned!\n\n**Reason:** " + reason)
			.addField("Current Warnings", String.valueOf(warnings), true);
			
		} else if (warnings < 9) {
			
			builder.setTitle("Muted " + event.getAuthor().getAsTag())
			.setColor(DARK_YELLOW).setDescription(event.getAuthor().getAsMention() + " has been muted!\n\n**Reason:** " + reason)
			.addField("Current Warnings", String.valueOf(warnings), true)
			.addField("Duration", punishment , true);
		
		} else if (warnings == 9) {
			
			builder.setTitle("Kicked " + event.getAuthor().getAsTag())
			.setColor(RED).
			setDescription(event.getAuthor().getAsMention() + " has been kicked!\n\n**Reason:** " + reason + " (9th Violation)")
			.addField("Current Warnings", String.valueOf(warnings), true);
			
		} else if (warnings >= 10) {
			
			builder.setTitle("Banned " + event.getAuthor().getAsTag())
			.setColor(DARK_RED).setDescription(event.getAuthor().getAsMention() + " has been warned!\n\n**Reason:** " + reason + " (" + warnings + "th Violation)")
			.addField("Current Warnings", String.valueOf(warnings), true);
	
		}
		
		event.getChannel().sendMessage(builder.build()).queue();
		return;
	}

    
    
    public static boolean containsSwear(String message) {
        ArrayList<String> swears = new ArrayList<String>();	
    	swears.add("bastard");
    	swears.add("bitch");
    	swears.add("bollocks");
    	swears.add("bullshit");
    	swears.add("cunt");
    	swears.add("effing");
    	swears.add("frigger");
    	swears.add("fuck");
    	swears.add("goddamn");
    	swears.add("godsdamn");
    	swears.add("horseshit");
    	swears.add("nigga");
    	swears.add("nigger");
    	swears.add("dick");
    	swears.add("prick");
    	swears.add("shit");
    	swears.add("shitass");
    	swears.add("slut");
    	swears.add("twat");
    	swears.add("whore");
    	swears.add("faggot");

        message = message.replaceAll("-", "").replaceAll("\0", "").replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a")
                .replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g");
        
    	String[] args = message.split(" ");
    	
    	
    for (int y = 0; y < args.length; y++) {
    	String word = args[y];
    	word = word.toLowerCase().replaceAll("[^a-zA-Z]", "");

    	
    	for (int x = 0; x < swears.size(); x++) {
    		if (word.equalsIgnoreCase(swears.get(x)) || word.contains(swears.get(x))) {
    			return true;
    		}
    	}
    }
     return false;
    }

}
