package me.mysticoverlord.mysticoverbot.commands.giveaway;

import java.awt.Color;
import java.util.List;

import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.ExceptionHandler;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import me.mysticoverlord.mysticoverbot.objects.SQLiteUtil;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Cancel implements ICommand{

	@Override
	public void handle(List<String> args, GuildMessageReceivedEvent event) {
		// TODO Auto-generated method stub
		if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
			event.getChannel().sendMessage("You don't have the Administrator Permission!").queue();
			return;
		}
		if (args.isEmpty()) {
			event.getChannel().sendMessage("No arguments were given.\n" + getUsage()).queue();
			return;
		}
		String Id = args.get(0);
		try {
		TextChannel channel = event.getGuild().getTextChannelById(SQLiteUtil.getGiveawayByID(Id));
		Message message = channel.retrieveMessageById(Id).complete();
		message.delete().queue();
		event.getChannel().sendMessage("Giveaway cancelled successfully.\nThe Message was deleted!").queue();
		} catch (Exception e) {
			event.getChannel().sendMessage("Something went wrong while trying to cancel the giveaway! Please check if you have provided the correct Id!").queue();
		}
	}
	
	@Override
	public String getHelp() {
		return "Cancels a giveaway";
	}

	@Override
	public String getInvoke() {
		// TODO Auto-generated method stub
		return "gcancel";
	}

	@Override
	public String getUsage() {
		// TODO Auto-generated method stub
		return "Usage: " + Constants.PREFIX + getInvoke() + " <messageId>";
	}

}
