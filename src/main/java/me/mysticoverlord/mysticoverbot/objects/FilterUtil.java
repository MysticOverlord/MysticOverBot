package me.mysticoverlord.mysticoverbot.objects;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class FilterUtil{
	
	public static Member getMemberByQuery(String input, GuildMessageReceivedEvent event, int n) {
		Member user = null;
		input = input.toLowerCase();
		for (int x = 0; x < event.getGuild().getMembers().size(); x++) {
    		if (event.getGuild().getMembers().get(x).getEffectiveName().toLowerCase().contains(input) || event.getGuild().getMembers().get(x).getUser().getName().toLowerCase().contains(input)) {
    			user = event.getGuild().getMembers().get(x);
    			break;
    		}
    	}
    	
    	if (user == null) {
    		try {
    			user = event.getGuild().getMemberById(input);
    		} catch (Exception e) {
    			try {
    			user = event.getMessage().getMentionedMembers().get(n);
    			} catch (IndexOutOfBoundsException e1) {
    				
    			}
    		}
    	}
    	return user;
    }
	
	public static Member getMemberByData(String input, GuildMessageReceivedEvent event, int n) {
		Member user = null;
    		try {
    			user = event.getGuild().getMemberById(input);
    		} catch (Exception e) {
    			try {
    			user = event.getMessage().getMentionedMembers().get(n);
    			} catch (IndexOutOfBoundsException e1) {
    				
    			}
    		}
    	return user;
    }
	
	public static TextChannel getChannelByData(String input, GuildMessageReceivedEvent event, int n) {
		TextChannel channel = null;
    		try {
    			channel = event.getGuild().getTextChannelById(input);
    		} catch (Exception e) {
    			try {
    			channel = event.getMessage().getMentionedChannels().get(n);
    			} catch (IndexOutOfBoundsException e1) {
    				
    			}
    		}
    	return channel;
    }
	
	
	public static boolean matchId(long id1, long id2) {
		boolean match = false;
    		if (id1 == id2) {
    			match = true;  
    		}
  			
			return match;
	}
	
	
   /* public static Integer isSFW(String url) {
    	HttpResponse<kong.unirest.JsonNode> response = Unirest.post("https://macgyverapi-nudity-detection-v1.p.rapidapi.com/")
    			.header("x-rapidapi-host", "macgyverapi-nudity-detection-v1.p.rapidapi.com")
    			.header("x-rapidapi-key", "4655854ea8msh69b19d243770808p1696d5jsn86c2fd994f26")
    			.header("content-type", "application/json")
    			.header("accept", "application/json")
    			.body("{    \"key\": \"free\",    \"id\": \"8E5q5T2p\",    \"data\": {        \"image\": \""+ url + "\"    }}")
    			.asJson();
    	return null;

}*/
}
