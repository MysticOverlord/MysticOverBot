/*
 * Decompiled with CFR 0.145.
 */
package me.mysticoverlord.mysticoverbot.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class ConfigLoader {
    ConfigLoader() {
    }

    String load(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }
}

