/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.music;

import com.sedmelluq.discord.lavaplayer.player.*;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.mysticoverlord.mysticoverbot.music.GuildMusicManager;
import me.mysticoverlord.mysticoverbot.objects.FormatUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.AudioManager;

public class PlayerManager {
    private static PlayerManager INSTANCE;
    private AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    private Map<Long, GuildMusicManager> musicManagers = new HashMap<Long, GuildMusicManager>();

    public PlayerManager() {
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
    }

    public synchronized GuildMusicManager getGuildMusicManager(Guild guild) {
    	long guildId = Long.parseLong(guild.getId());
        GuildMusicManager musicManager = musicManagers.get(guildId);

        if (musicManager == null) {
          musicManager = new GuildMusicManager(playerManager, guild);
          musicManagers.put(guildId, musicManager);
        }

        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());

        return musicManager;
    }

    
    /**
     * Load a track and add it to the queue
     * @param channel
     * @param trackUrl
     * @param author
     */
   public void loadAndPlay(TextChannel channel, Guild guild, String trackUrl, String author) {
        GuildMusicManager musicManager = getGuildMusicManager(guild);
        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler(){

            @Override
            public void trackLoaded(AudioTrack track) {
            	track.setUserData(author);
            	if (channel != null) {
                if (musicManager.player.getPlayingTrack() == null) {
                    channel.sendMessage("Now Playing:\n" + track.getInfo().title).queue();
                } else {
                    String url = track.getInfo().uri;
                    url = String.valueOf(url.replace("https://www.youtube.com/watch?v=", "https://i.ytimg.com/vi/")) + "/hqdefault.jpg";
                    EmbedBuilder builder = EmbedUtils.getDefaultEmbed().setThumbnail(url)
                    		.setAuthor("Added to queue")
                    		.setTitle(track.getInfo().title, trackUrl)
                    		.addField("Channel", track.getInfo().author, true)
                    		.addField("Song Duration", FormatUtil.formatTime(track.getInfo().length), true)
                    		.addField("Position in Queue", Integer.toString(musicManager.scheduler.getQueue().size() + 1), false).setFooter("Added to queue  ", guild.getJDA().getUserById((author)).getEffectiveAvatarUrl());
                    channel.sendMessage(builder.build()).queue();
                }
            	}
                PlayerManager.play(musicManager, track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getSelectedTrack();
                if (firstTrack == null) {
                    firstTrack = playlist.getTracks().remove(0);
                }
                channel.sendMessage("Adding to queue " + firstTrack.getInfo().title + " (first track of playlist " + playlist.getName() + ")").queue();
                PlayerManager.play(musicManager, firstTrack);
                playlist.getTracks().forEach(musicManager.scheduler::queue);
            }

            @Override
            public void noMatches() {
                channel.sendMessage("Nothing found by " + trackUrl).queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                channel.sendMessage("Could not play: " + exception.getMessage()).queue();
            }
        });
    }
    
   
   public void loadAndPlay(Guild guild, String trackUrl, String author) {
       GuildMusicManager musicManager = getGuildMusicManager(guild);
       playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler(){

           @Override
           public void trackLoaded(AudioTrack track) {
           	track.setUserData(author);
               PlayerManager.play(musicManager, track);
           }

           @Override
           public void playlistLoaded(AudioPlaylist playlist) {
               AudioTrack firstTrack = playlist.getSelectedTrack();
               if (firstTrack == null) {
                   firstTrack = playlist.getTracks().remove(0);
               }
             PlayerManager.play(musicManager, firstTrack);
               playlist.getTracks().forEach(musicManager.scheduler::queue);
           }

           @Override
           public void noMatches() {
           }

           @Override
           public void loadFailed(FriendlyException exception) {
           }
       });
   }
    
    /**
     * Load a track to from top of the queue
     * @param channel
     * @param trackUrl
     * @param author
     */
    public void loadFromTop(TextChannel channel, Guild guild, String trackUrl, String author) {
    	 GuildMusicManager musicManager = getGuildMusicManager(guild);
         BlockingQueue<AudioTrack> queue = musicManager.scheduler.getQueue();
         int trackCount = queue.size();
         ArrayList<AudioTrack> tracks = new ArrayList<AudioTrack>(queue);
         String trackUri;
         queue.clear();
         
    	 for (int x = 0; x < trackCount + 1; x++) {
    		 
    		 if (x == 0) {
    			 trackUri = trackUrl;
    			 playerManager.loadItemOrdered(musicManager, trackUri, new AudioLoadResultHandler(){
    			     	
    	             @Override
    	             public void trackLoaded(AudioTrack track) { 
    	            	 track.setUserData(author);
    	            	 if (channel != null) {
    	            	 if (musicManager.player.getPlayingTrack() == null) {
    	                 	
    	                     channel.sendMessage("Now Playing:\n" + track.getInfo().title).queue();
    	                 } else {
    	                     String url = trackUrl;
    	                     url = String.valueOf(url.replace("https://youtu.be/", "https://i.ytimg.com/vi/")) + "/hqdefault.jpg";
    	                     EmbedBuilder builder = EmbedUtils.getDefaultEmbed().setThumbnail(url)
    	                     		.setAuthor("Added to queue")
    	                     		.setTitle(track.getInfo().title, trackUrl)
    	                     		.addField("Channel", track.getInfo().author, true)
    	                     		.addField("Song Duration", FormatUtil.formatTime(track.getInfo().length), true)
    	                     		.addField("Position in Queue", Integer.toString(musicManager.scheduler.getQueue().size() + 1), false).setFooter("Added to queue  ", guild.getJDA().getUserById(author).getAvatarUrl());
    	                     channel.sendMessage(builder.build()).queue();
    	                 }
    	            	 }
    	                 PlayerManager.play(musicManager, track);
    	             }

    	 			@Override
    	 			public void playlistLoaded(AudioPlaylist playlist) {
    	 				// TODO Auto-generated method stub
    	 				AudioTrack firstTrack = playlist.getSelectedTrack();
    	                 if (firstTrack == null) {
    	                     firstTrack = playlist.getTracks().remove(0);
    	                 }
    	                 PlayerManager.play(musicManager, firstTrack);
    	                 playlist.getTracks().forEach(musicManager.scheduler::queue);
    	 			}

    	 			public void noMatches() {
    	 				if (channel == null) {return;}
    	                channel.sendMessage("Nothing found by " + trackUrl).queue();
    	            }

    	            @Override
    	            public void loadFailed(FriendlyException exception) {
    	            	if (channel == null) {return;}
    	                channel.sendMessage("Could not play: " + exception.getMessage()).queue();
    	            }
    	         });   			
    		 } else {
    	         	trackUri = tracks.get(x).getInfo().uri;   		 

     	playerManager.loadItemOrdered(musicManager, trackUri, new AudioLoadResultHandler(){
     	
             @Override
             public void trackLoaded(AudioTrack track) { 
                 PlayerManager.play(musicManager, track);
             }
 			@Override
 			public void playlistLoaded(AudioPlaylist playlist) {
 				// TODO Auto-generated method stub
 				AudioTrack firstTrack = playlist.getSelectedTrack();
                 if (firstTrack == null) {
                     firstTrack = playlist.getTracks().remove(0);
                 }
                 PlayerManager.play(musicManager, firstTrack);
                 playlist.getTracks().forEach(musicManager.scheduler::queue);
 			}
 			public void noMatches() {}
            @Override
            public void loadFailed(FriendlyException exception) {}            
         });
     	} 
    		 }
         }
    
    /**
     * Remove a track from the queue
     * @param song
     * @param channel
     */
    public void removeFromQueue(int song, TextChannel channel, Member member) {
    	 GuildMusicManager musicManager = getGuildMusicManager(channel.getGuild());
        BlockingQueue<AudioTrack> queue = musicManager.scheduler.getQueue();
        int trackCount = queue.size();
        ArrayList<AudioTrack> tracks = new ArrayList<AudioTrack>(queue);
        if (!tracks.get(song).getUserData().equals(member.getId()) && !member.hasPermission(Permission.MANAGE_SERVER) && !isDJ(member)) {
        	channel.sendMessage("You can't remove a track someone else requested!\nOnly people with a ``DJ`` role or ``Manage Server`` Permissions can do that!").queue();
        	return;
        }
            
        String trackUrl;
        queue.clear();

        for (int x = 0; x < trackCount; x++) {
        	if (x != song) {
        		String id = (String) tracks.get(x).getUserData();
        	trackUrl = tracks.get(x).getInfo().uri;
    	playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler(){
    	
            @Override
            public void trackLoaded(AudioTrack track) {
            	track.setUserData(id);
                PlayerManager.play(musicManager, track);
            }

			@Override
			public void playlistLoaded(AudioPlaylist playlist) {
				// TODO Auto-generated method stub
				AudioTrack firstTrack = playlist.getSelectedTrack();
                if (firstTrack == null) {
                    firstTrack = playlist.getTracks().remove(0);
                }
                PlayerManager.play(musicManager, firstTrack);
                playlist.getTracks().forEach(musicManager.scheduler::queue);
			}

			@Override
			public void noMatches() {
			}
			@Override
			public void loadFailed(FriendlyException exception) {	
			}

            
        });
    	} else {
    		
    		channel.sendMessage("Track number " + (song + 1) + "\n" + tracks.get(x).getInfo().title + " was removed!").queue();

    	}
        }
    
    }
    

    private static void play(GuildMusicManager musicManager, AudioTrack track) {
        musicManager.scheduler.queue(track);
    }
    
    @SuppressWarnings("unused")
	private void play(Guild guild, GuildMusicManager musicManager, AudioTrack track) {
        connectToFirstVoiceChannel(guild.getAudioManager());

        musicManager.scheduler.queue(track);
      }
    
    private static void connectToFirstVoiceChannel(AudioManager audioManager) {
        if (!audioManager.isConnected() && !audioManager.isConnected()) {
          for (VoiceChannel voiceChannel : audioManager.getGuild().getVoiceChannels()) {
            audioManager.openAudioConnection(voiceChannel);
            break;
          }
        }
      }

    public static synchronized PlayerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }
    
    private Boolean isDJ(Member member) {
    for (int x = 0; x < member.getRoles().size(); x++) {
    	if (member.getRoles().get(x).getName().equalsIgnoreCase("DJ")) {
    		return true;
    	}
    }
    	return false;
    }

}

