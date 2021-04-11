/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.python.modules.math;

import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.music.GuildMusicManager;
import me.mysticoverlord.mysticoverbot.music.PlayerManager;
import me.mysticoverlord.mysticoverbot.music.TrackScheduler;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import me.mysticoverlord.mysticoverbot.objects.IMusic;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class Skip
implements IMusic {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
        TrackScheduler scheduler = musicManager.scheduler;
        AudioManager audio = event.getGuild().getAudioManager();
        AudioPlayer player = musicManager.player;
        if (player.getPlayingTrack() == null) {
            channel.sendMessage("No song is currently being played").queue();
            return;
        }
        VoiceChannel vc = audio.getConnectedChannel();
        if (!vc.getMembers().contains(event.getMember())) {
            channel.sendMessage("You have to be in my voice channel to skip songs!").queue();
            return;
        }
        Integer size = (int) vc.getMembers().stream().distinct().filter(u -> !u.getUser().isBot()).count();
        
        Map<Long, Boolean> map;
     
 
        if (Constants.votes.containsKey(event.getGuild().getIdLong())){
        	map = Constants.votes.get(event.getGuild().getIdLong());
        } else {
        	map = new HashMap<Long, Boolean>();
        }
        
        if (map.containsKey(event.getAuthor().getIdLong())) {
        	event.getChannel().sendMessage("You already voted!").queue();
        	return;
        }
        map.put(event.getAuthor().getIdLong(), true);        
        Constants.votes.put(event.getGuild().getIdLong(), map);
        
        int num;
        if (size == 3) {
        	num = 2;
        } else {
        	num = (int) math.ceil((double)size / 2.0);
        			
        }
        
      
        
        if (Constants.votes.get(event.getGuild().getIdLong()).size() >= num) {
            Constants.votes.remove(event.getGuild().getIdLong());
            scheduler.nextTrack();
            channel.sendMessage("Song skipped!").queue();
        } else if (isDJ(event.getMember()) || event.getMember().hasPermission(Permission.MANAGE_SERVER) || event.getMember().getId().equalsIgnoreCase((String)player.getPlayingTrack().getUserData()) || vc.getMembers().size() <= 2) {

            Constants.votes.remove(event.getGuild().getIdLong());
        	scheduler.nextTrack();
             channel.sendMessage("Song skipped!").queue();
        } else {
        	channel.sendMessage("Skip Votes: (" + Constants.votes.get(event.getGuild().getIdLong()).size() + "/" + num + ")").queue();
        }
       
        
    }

    @Override
    public String getHelp() {
        return "Skips playing track";
    }

    @Override
    public String getInvoke() {
        return "skip";
    }

    @Override
    public String getUsage() {
        return "Usage: " + Constants.PREFIX + this.getInvoke();
    }
    
    private Boolean isDJ(Member member) {
    for (int x = 0; x < member.getRoles().size(); x++) {
    	if (member.getRoles().get(x).getName().equalsIgnoreCase("DJ")) {
    		return true;
    	}
    }
    	return false;
    }

	@Override
	public String getAlias() {
		// TODO Auto-generated method stub
		return "s";
	}
}

