/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import me.mysticoverlord.mysticoverbot.Constants;
import net.dv8tion.jda.api.entities.Guild;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackScheduler
extends AudioEventAdapter {
    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue;
    private final Guild guild;
    PlayerManager manager = PlayerManager.getInstance();

    public TrackScheduler(AudioPlayer player, Guild guild) {
        this.player = player;
        this.queue = new LinkedBlockingQueue<AudioTrack>();
        this.guild = guild;
    }

    public void queue(AudioTrack track) {
        if (!this.player.startTrack(track, true)) {
            this.queue.offer(track);
        }
    }

    public BlockingQueue<AudioTrack> getQueue() {
        return this.queue;
    }
    
    //TODO test this
    public void setQueue(ArrayList<AudioTrack> queue) {
    	queue.forEach((track) -> {
    		this.queue.add(track);
    	});
    }

    public void nextTrack() {
        this.player.startTrack((AudioTrack)this.queue.poll(), false);
    }

	@Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
        	if (Constants.loop.containsKey(this.guild.getIdLong())) {
	        	if (Constants.loop.get(this.guild.getIdLong())) {
	                manager.loadFromTop(null, this.guild, track.getInfo().uri, "Loop");
	                return;
	             }
        	}
            this.nextTrack();
        }
        if (Constants.loopqueue.containsKey(this.guild.getIdLong()))  {
            if (Constants.loopqueue.get(this.guild.getIdLong())) {
                manager.loadAndPlay(null, this.guild, track.getInfo().uri, "Loopqueue");
             }
        }
    }
}

