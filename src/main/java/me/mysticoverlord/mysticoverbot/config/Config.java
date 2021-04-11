/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.config;

import java.io.File;
import java.io.IOException;
import me.mysticoverlord.mysticoverbot.config.ConfigLoader;
import org.json.JSONObject;

public class Config
extends JSONObject {
    private static Config instance;

    public Config(File file) throws IOException {
        super(new ConfigLoader().load(file));
        instance = this;
    }

    public static Config getInstance() {
        return instance;
    }
}

