package me.mysticoverlord.mysticoverbot.objects;

import java.io.PrintWriter;
import java.io.StringWriter;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.mysticoverlord.mysticoverbot.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class ExceptionHandler {

	public static void handle(Exception e) {
		StringWriter sw = new StringWriter();
    	e.printStackTrace(new PrintWriter(sw));
    	String exceptionAsString = sw.toString();
    	
    	TextChannel channel = Constants.channel;
    	EmbedBuilder builder = EmbedUtils.getDefaultEmbed()
    			.setTitle("Error occured during Execution")
    			.setDescription(e.getMessage() + "\n" + e.getLocalizedMessage())
    			.addField("Class", e.getClass().getName(), true);
       if (exceptionAsString.length() >= 1024) {
    	   for (int x = 0; x <= exceptionAsString.length() / 1024; x++) {
    		   try {
    		   builder.addField("StackTrace [" + (x + 1) + "]", exceptionAsString.substring(x * 1024, (x+1) * 1024), false);
    		   } catch (StringIndexOutOfBoundsException e1) {
    			   builder.addField("StackTrace [" + (x + 1) + "]", exceptionAsString.substring(x * 1024), false);
    		   }
    	   }
    	} else {
    		builder.addField("StackTrace", exceptionAsString, true);
    	}
    	channel.sendMessage(builder.build()).queue();
	}
}
