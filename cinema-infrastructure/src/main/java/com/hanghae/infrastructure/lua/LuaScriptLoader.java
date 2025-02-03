package com.hanghae.infrastructure.lua;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class LuaScriptLoader {
    public static String loadScript(String resourcePath) {
        try (InputStream inputStream = LuaScriptLoader.class.getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            if (inputStream == null) {
                throw new RuntimeException("Lua script not found: " + resourcePath);
            }

            return reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load Lua script: " + resourcePath, e);
        }
    }
}
