package me.mysticoverlord.mysticoverbot.commands;

import java.time.format.DateTimeFormatter;
import java.util.List;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.mysticoverlord.mysticoverbot.CommandManager;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.objects.FormatUtil;
import me.mysticoverlord.mysticoverbot.objects.ICommand;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Botinfo implements ICommand {
	Integer commandCount = 0;
	
	public Botinfo(Integer count) {
		commandCount = count;
	}

	@Override
	public void handle(List<String> args, GuildMessageReceivedEvent event) {
		// TODO Auto-generated method stub
		User user = event.getJDA().getSelfUser();
		
		event.getMessage().getReactions();
		
		HttpResponse<JsonNode> response = Unirest.get("https://top.gg/api/bots/576061124043210759")
				.header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjU3NjA2MTEyNDA0MzIxMDc1OSIsImJvdCI6dHJ1ZSwiaWF0IjoxNTY1MTExNzgwfQ.P0OX3YN_waIKd1U6YoZURRcIrAsIRd61NuQ6oHOSYvw")
				.asJson();
		
		int object = (int) response.getBody().getObject().get("monthlyPoints");
    	String monthlyPoints = String.valueOf(object);
    	object = (int) response.getBody().getObject().get("points");
    	String points = String.valueOf(object);
		
		MessageEmbed builder = EmbedUtils.getDefaultEmbed()
				.setThumbnail(user.getEffectiveAvatarUrl())
				.setAuthor("Creator: MysticOverlord#7967", "https://discord.gg/kUVCU7z", event.getJDA().getUserById(Constants.OWNER).getAvatarUrl())
				.addField("Bot ID", user.getId(), true)
				.addField("Discriminator", user.getDiscriminator(), true)
				.addField("Version", Constants.VERSION, true)
				.addField("Command Categories", "6", true)
				.addField("Commands", String.valueOf(commandCount), true)
				.addField("Servers Watched", String.valueOf(event.getJDA().getGuilds().size()), false)
			    .addField("Users Watched", String.valueOf(event.getJDA().getUsers().size()), true)
				.addField("Time Created", user.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME), false)
			    .addField("Monthly Votes", monthlyPoints, true)
			    .addField("Total Votes", points, true)
				.addField("JDA Version", "4.2.0_227", true)
				.addField("Java Version", "JavaSE-1.11 (jdk-11.0.7)", true)
				.addField("Links", embedLink("https://discord.gg/kUVCU7z", "Server Invite") + "\n"
			+ embedLink("https://discordapp.com/oauth2/authorize?client_id=576061124043210759&permissions=1010695671&scope=bot", "Invite Bot" ) + "\n"
					+ embedLink("https://www.patreon.com/MysticOverBot", "Patreon") + "\n" + FormatUtil.embedLink("https://top.gg/bot/576061124043210759/vote", "Vote for me on top.gg"), false)
				.build();
				event.getChannel().sendMessage(builder).queue();

	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "Get the bot info";
	}

	@Override
	public String getInvoke() {
		// TODO Auto-generated method stub
		return "botinfo";
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