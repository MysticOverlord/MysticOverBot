package me.mysticoverlord.mysticoverbot.commands;

import java.time.format.DateTimeFormatter;
import java.util.List;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ServerInfo implements ICommand{

	@Override
	public void handle(List<String> args, GuildMessageReceivedEvent event) {
		// TODO Auto-generated method stub
		int bots = 0;
		Guild guild = event.getGuild();
		
		EmbedBuilder builder = EmbedUtils.getDefaultEmbed();
		try {
		builder.setThumbnail(guild.getIconUrl());
		} catch (NullPointerException e) {
			
		}
		builder.setTitle(guild.getName()  + " Info")
		.addField("Owner", guild.getOwner().getAsMention(), false)
		.addField("Creation Date", guild.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME), true)
		.addField("Server Region", guild.getRegionRaw(), true)
		.addField("Member Count", String.valueOf(guild.getMemberCount()), true);
		
		for (int x = 0; x < guild.getMembers().size(); x++) {
			if (event.getJDA().getUserById(guild.getMembers().get(x).getId()).isBot()) {
				bots += 1;
			}
		}
		
		builder.addField("User Members", String.valueOf(guild.getMembers().size() - bots), true)
		.addField("Bot Members", String.valueOf(bots), true)
		.addField("Nitro Boosters", String.valueOf(guild.getBoostCount()), true)
		.addField("Boost Tier", guild.getBoostTier().toString(), false)
		.addField("Category Count", String.valueOf(guild.getCategories().size()), true)
		.addField("Channel Count", String.valueOf(guild.getChannels().size() - guild.getCategories().size()), true)
		.addField("Text Channel Count", String.valueOf(guild.getTextChannels().size()), true)
		.addField("Voice Channel Count", String.valueOf(guild.getVoiceChannels().size()), true);
		
		event.getChannel().sendMessage(builder.build()).queue();
		

		
		
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "display server details";
	}

	@Override
	public String getInvoke() {
		// TODO Auto-generated method stub
		return "serverinfo";
	}

	@Override
	public String getUsage() {
		// TODO Auto-generated method stub
		return "Usage: " + Constants.PREFIX + getInvoke();
	}

}
