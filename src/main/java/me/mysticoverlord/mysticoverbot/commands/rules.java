package me.mysticoverlord.mysticoverbot.commands;

import java.util.List;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class rules implements ICommand{

	@Override
	public void handle(List<String> args, GuildMessageReceivedEvent event) {
		// TODO Auto-generated method stub
		EmbedBuilder builder = EmbedUtils.getDefaultEmbed()
				.setTitle("Usage Rules for o!suggest and o!reportbug")
				.setDescription("1. Do not use for anything unrelated to their Commands.\n"
						+ "This includes, but is not limited to\nAdvertisements, Phishing or anything that is not a suggestion or a bug!\n\n"
						+ "2. No excessive use of the commands.\n"
						+ "This includes Spam, Flooding and dividing one message into many.\n\n"
						+ "3. No NSFW suggestions!\nThis includes, but is not limited to, Porn, Gore, Nudity, suggestive features and borderline thereoff\n\n"
						+ "4. Use Common sense!\nDon't suggest nonsense or absolute stupidity!\n\n"
						+ "5. __No Links or files are to be put in the suggestion!__\n\n"
						+ "6. **Every** suggestion is to be made in English!\nDoing otherwise will lead to your suggestion and/or bug being ignored.\nRepeated offense leads to ban of the Usage of these commands!\n\n\n"
						+ "There will be one warning at most after violation, depending on severity.\nAbuse of o!reportbug will lead to your guild being excluded from the report bug feature!");
		event.getChannel().sendMessage(builder.build()).queue();
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override 
	public String getInvoke() {
		// TODO Auto-generated method stub
		return "rules";
	}

	@Override
	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}

}
