/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import java.nio.ByteBuffer;
import net.dv8tion.jda.api.audio.AudioSendHandler;

public class AudioPlayerSendHandler
implements AudioSendHandler {
    private final AudioPlayer audioPlayer;
    private AudioFrame lastFrame;

    public AudioPlayerSendHandler(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public boolean canProvide() {
        if (this.lastFrame == null) {
            this.lastFrame = this.audioPlayer.provide();
        }
        return this.lastFrame != null;
    }

    @Override
    public ByteBuffer provide20MsAudio() {
        if (this.lastFrame == null) {
            this.lastFrame = this.audioPlayer.provide();
        }
        byte[] data = this.lastFrame != null ? this.lastFrame.getData() : null;
        this.lastFrame = null;
        return ByteBuffer.wrap(data);
    }

    @Override
    public boolean isOpus() {
        return true;
    }
}

