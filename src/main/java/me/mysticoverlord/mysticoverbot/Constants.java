/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot;


import java.util.HashMap;
import java.util.Map;
import org.discordbots.api.client.DiscordBotListAPI;

import net.dv8tion.jda.api.entities.TextChannel;

public class Constants {
	public static final String configpath = Main.config.getString("configpath");
	public static final String databasepath = Main.config.getString("databasepath");
	public static final Map<Long, Map<Long, Boolean>> votes = new HashMap<Long, Map<Long, Boolean>>();
	public static final Map<Long, Boolean> loop = new HashMap<Long, Boolean>();
	public static final Map<Long, Boolean> loopqueue = new HashMap<Long, Boolean>();
	public static String VERSION = "V.2.3.2";
    public static final long OWNER = 394066807926947850L;
    public static TextChannel channel;
    public static String PREFIX = "o!";
    public static DiscordBotListAPI api = new DiscordBotListAPI.Builder().token(Main.config.getString("topggtoken")).botId("576061124043210759").build();
}

