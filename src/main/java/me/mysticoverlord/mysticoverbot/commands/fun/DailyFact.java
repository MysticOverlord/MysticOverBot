package me.mysticoverlord.mysticoverbot.commands.fun;

import java.util.List;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class DailyFact implements ICommand{

	@Override
	public void handle(List<String> args, GuildMessageReceivedEvent event) {
		// TODO Auto-generated method stub
	
	 WebUtils.ins.getJSONObject("https://uselessfacts.jsph.pl/today.json?language=en").async(json -> {
         String text = json.get("text").asText();
         MessageEmbed embed = EmbedUtils.getDefaultEmbed().setTitle("Daily Fact")
        		 .setDescription(text)
        		 .setFooter("Issued by " + event.getAuthor().getAsTag(), event.getAuthor().getAvatarUrl())
        		 .build();
         event.getChannel().sendMessage(embed).queue();
     });
		
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "Gives you the daily *also probably useless* fact";
	}

	@Override
	public String getInvoke() {
		// TODO Auto-generated method stub
		return "dailyfact";
	}

	@Override
	public String getUsage() {
		// TODO Auto-generated method stub
		return "Usage: " + Constants.PREFIX + getInvoke();
	}

}
