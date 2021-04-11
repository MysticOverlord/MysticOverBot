/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import java.util.List;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.mysticoverlord.mysticoverbot.Constants;
import me.mysticoverlord.mysticoverbot.music.GuildMusicManager;
import me.mysticoverlord.mysticoverbot.music.PlayerManager;
import me.mysticoverlord.mysticoverbot.objects.FormatUtil;
import me.mysticoverlord.mysticoverbot.objects.ICommand;
import me.mysticoverlord.mysticoverbot.objects.IMusic;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Playing
implements IMusic {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
        AudioPlayer player = musicManager.player;
        if (player.getPlayingTrack() == null) {
            channel.sendMessage("No song is being played!").queue();
            return;
        }
        int position = (int) player.getPlayingTrack().getPosition();
        int duration = (int) player.getPlayingTrack().getDuration();
        double percent = (double)position/duration;
        AudioTrackInfo info = player.getPlayingTrack().getInfo();
        String status = "";
        String user;
        if (!player.getPlayingTrack().getUserData().equals(null)) {
        	try {
        	user = event.getJDA().getUserById((String) player.getPlayingTrack().getUserData()).getAsMention();
        	} catch (NumberFormatException e) {
        		user = player.getPlayingTrack().getUserData().toString();
        	}
        } else {

        	user = "not registered";
        }
        status = player.isPaused() ? ":pause_button: " : ":arrow_forward: ";
        EmbedBuilder builder = EmbedUtils.getDefaultEmbed()
        		.setTitle("Now Playing", info.uri)
        		.setDescription(info.title + "\n\n"
        		+ status + " "
        		+ FormatUtil.formatTime(player.getPlayingTrack().getPosition()) + " - " + FormatUtil.formatTime(player.getPlayingTrack().getDuration()) + "\n\n"
        		+ FormatUtil.progressBar(percent)
        		+ "\n" + FormatUtil.volumeIcon(player.getVolume()) + " (" + player.getVolume() + "/100)")
        		.addField("Requested By", user , true);
        channel.sendMessage(builder.build()).queue();

    }

    @Override
    public String getHelp() {
        return "Displays the currently playing track";
    }

    @Override
    public String getInvoke() {
        return "np";
    }

    @Override
    public String getUsage() {
        return "Usage: " + Constants.PREFIX + this.getInvoke();
    }

	@Override
	public String getAlias() {
		// TODO Auto-generated method stub
		return "np";
	}
}

