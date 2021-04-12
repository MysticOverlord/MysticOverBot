/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import me.mysticoverlord.mysticoverbot.config.Config;
import me.mysticoverlord.mysticoverbot.database.SQLiteDataSource;
import me.mysticoverlord.mysticoverbot.objects.Encryption;
import me.mysticoverlord.mysticoverbot.objects.ExceptionHandler;
import me.mysticoverlord.mysticoverbot.objects.SQLiteUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final Color PURPLE = new Color(128, 0, 128);
    public static final Config config = new Config(new File(Constants.configpath)); 

	 public Main() throws IOException, SQLException {
	    Logger logger = LoggerFactory.getLogger(Main.class);
    	SQLiteDataSource.getConnection();
    	logger.debug("SQLite database connection established!");
    	SQLiteUtil.encryption = new Encryption(config.getString("key").toCharArray(), config.getString("salt").getBytes());
    	logger.debug("Encryption services initialized");
    	 
        CommandManager commandManager = new CommandManager();
        Listener listener = new Listener(commandManager);
        
        WebUtils.setUserAgent("Mozilla/5.0 MyticOverbot/MysticOverlord#7967");
        
        EmbedUtils.setEmbedBuilder(() -> new EmbedBuilder()
        		.setColor(PURPLE)
        		.setTimestamp(Instant.now()));
        try {
            logger.info("Booting");
             JDABuilder.createDefault(config.getString("token"))
            .setStatus(OnlineStatus.DO_NOT_DISTURB)
            .setActivity(Activity.playing("Starting up..."))
            .addEventListeners(listener, new ModListener())
            .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES)
            .enableCache(CacheFlag.VOICE_STATE)
            .setChunkingFilter(ChunkingFilter.ALL)
            .build()
            .awaitReady();
            logger.info("Running");
        }
        catch (Exception e) {      
        	ExceptionHandler.handle(e);     
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        new Main();
    }
}

