package me.mysticoverlord.mysticoverbot.commands.music;

import java.util.List;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.mysticoverlord.mysticoverbot.music.GuildMusicManager;
import me.mysticoverlord.mysticoverbot.music.PlayerManager;
import me.mysticoverlord.mysticoverbot.music.TrackScheduler;
import me.mysticoverlord.mysticoverbot.objects.FormatUtil;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import me.mysticoverlord.mysticoverbot.objects.IMusic;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class Volume implements IMusic{

	@Override
	public void handle(List<String> args, GuildMessageReceivedEvent event) {
		event.getChannel().sendMessage("This Command has been temporarily disabled due to an unknown error which causes the entire music feature to break down!").queue();
       /* TextChannel channel = event.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
        TrackScheduler scheduler = musicManager.scheduler;
        AudioManager audio = event.getGuild().getAudioManager();
        AudioPlayer player = musicManager.player;
		// TODO Auto-generated method stub
		if (args.isEmpty()) {
        	event.getChannel().sendMessage("Please provide a number between 0 and 100!").queue();
        	return;
        }
		
        if (player.getPlayingTrack() == null) {
            channel.sendMessage("No song is currently being played").queue();
            return;
        }
        VoiceChannel vc = audio.getConnectedChannel();
        if (!vc.getMembers().contains(event.getMember())) {
            channel.sendMessage("You have to be in my voice channel to skip songs!").queue();
            return;
        }
        
        int volume;
        try {
        	volume = Integer.parseInt(args.get(0));
        } catch (NumberFormatException e) {
        	event.getChannel().sendMessage("The given parameter is not a number!").queue();
        	return;
        }
        
        if (volume < 0 || volume > 100) {
        	event.getChannel().sendMessage("Please provide a number between 1 and 100!").queue();
        }
	    player.setVolume(volume); 
		event.getChannel().sendMessage("Volume set to " + volume + " " + FormatUtil.volumeIcon(volume)).queue();
		*/
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInvoke() {
		// TODO Auto-generated method stub
		return "volume";
	}

	@Override
	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlias() {
		// TODO Auto-generated method stub
		return "v";
	}

}
