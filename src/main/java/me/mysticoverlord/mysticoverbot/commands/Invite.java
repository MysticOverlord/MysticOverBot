package me.mysticoverlord.mysticoverbot.commands;

import java.util.List;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.FormatUtil;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Invite implements ICommand {

	@Override
	public void handle(List<String> args, GuildMessageReceivedEvent event) {
		// TODO Auto-generated method stub
		EmbedBuilder builder = EmbedUtils.getDefaultEmbed()
			.setTitle("Invite")
			.addField("Server Invite",embedLink("https://discord.gg/kUVCU7z", "Server Invite"), true)
			.addField("Bot Invite", embedLink("https://discordapp.com/oauth2/authorize?client_id=576061124043210759&permissions=8&scope=bot", "Invite Bot" ), true)
			.addField("Vote if you like me", FormatUtil.embedLink("https://top.gg/bot/576061124043210759/vote", "top.gg"), true)
			.addField("Patreon", embedLink("https://www.patreon.com/MysticOverBot", "Patreon"), false);
		
		event.getChannel().sendMessage(builder.build()).queue();
		}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "Gives you the bot invite and server invite";
	}

	@Override
	public String getInvoke() {
		// TODO Auto-generated method stub
		return "invite";
	}

	@Override
	public String getUsage() {
		// TODO Auto-generated method stub
		return "Usage: " + Constants.PREFIX + getInvoke();
	}

	
    public static String embedLink(String link, String text) {
        return "[" + text + "](" + link +  ")";
    }
	
}
