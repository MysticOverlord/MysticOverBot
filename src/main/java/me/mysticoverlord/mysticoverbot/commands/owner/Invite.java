package me.mysticoverlord.mysticoverbot.commands.owner;

import java.util.List;

import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Invite implements ICommand {

	@Override
	public void handle(List<String> args, GuildMessageReceivedEvent event) {
		// TODO Auto-generated method stub
		User author = event.getAuthor();
		
		if (author.getIdLong() == Constants.OWNER) {
			author.openPrivateChannel().queue((channel) -> {

				event.getJDA().getGuilds().get(1).retrieveInvites().complete().get(0).toString();
			});
		}
		
		
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInvoke() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}

}
