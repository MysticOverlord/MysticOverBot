package me.mysticoverlord.mysticoverbot.commands.image;


import java.io.File;
import java.util.List;


import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Warped implements ICommand{
   

     
	@Override
	public void handle(List<String> args, GuildMessageReceivedEvent event) {
		// TODO Auto-generated method stub
		


        String joined = String.join("", args);
       
		@SuppressWarnings("unused")
		HttpResponse<File> response = Unirest.get("https://dankmemer.services/api/changemymind")
			      .header("Authorization", "39ade65eff4af366358072121a14c1758b2b0d49355fcf41a609649d150c1edb")
			      .queryString("text", joined )
			      .asFile("C:\\Users\\Dieser PC\\Documents\\Aufgabe 2\\changemymind.jpg");
		
		File file = new File("C:\\Users\\Dieser PC\\Documents\\Aufgabe 2\\changemymind.jpg");

		event.getChannel().sendFile(file, file.getName()).queue();


	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInvoke() {
		// TODO Auto-generated method stub
		return "changemymind";
	}

	@Override
	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}


}
