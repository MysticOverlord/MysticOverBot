package me.mysticoverlord.mysticoverbot.commands.music;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import me.mysticoverlord.mysticoverbot.music.GuildMusicManager;
import me.mysticoverlord.mysticoverbot.music.PlayerManager;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import me.mysticoverlord.mysticoverbot.objects.IMusic;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class Remove implements IMusic{

	@Override
	public void handle(List<String> args, GuildMessageReceivedEvent event) {
		// TODO Auto-generated method stub
		TextChannel channel = event.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
        BlockingQueue<AudioTrack> queue = musicManager.scheduler.getQueue();
		 AudioPlayer player = musicManager.player;
	        AudioManager audio = event.getGuild().getAudioManager();
	        
	        VoiceChannel vc = audio.getConnectedChannel();
	        if (!vc.getMembers().contains(event.getMember())) {
	            channel.sendMessage("You have to be in my voice channel to remove songs!").queue();
	            return;
	        }
	        if (player.getPlayingTrack().equals(null)) {
	        	channel.sendMessage("No song is currently being played").queue();
	        	return;
	        }
	        AudioTrackInfo in = player.getPlayingTrack().getInfo();
	        if (queue.isEmpty()) {
	            channel.sendMessage("The queue is empty!").queue();
	            return;
	        }
	        if (args.isEmpty()) {
	        	event.getChannel().sendMessage("Please provide a queue number!").queue();
	        	return;
	        }
	        
	        int remove;
	        try {
	        	remove = Integer.parseInt(args.get(0)) - 1;
	        } catch (NumberFormatException e) {
	        	event.getChannel().sendMessage("The given parameter is not a number!").queue();
	        	return;
	        }
	        
            try {
            playerManager.removeFromQueue(remove, event.getChannel(), event.getMember());
            } catch (IndexOutOfBoundsException e) {
            	channel.sendMessage("The song you are trying to remove doesn't exist").queue();
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
		return "remove";
	}

	@Override
	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlias() {
		// TODO Auto-generated method stub
		return "r";
	}

}
