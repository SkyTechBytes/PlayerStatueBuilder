package com.skytechbytes.playerstatuebuilder.lookup;

import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilderException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MojangUUIDFetcher implements UUIDFetcher {
    // Regex to locate the "id" field in the profile JSON: {"name":"Notch","id":"069a79f4..."}
    private static final Pattern ID_PATTERN = Pattern.compile(
            "\"id\"\\s*:\\s*\"([a-fA-F0-9]{32})\""
    );

    @Override
    public String getUUID(String username) throws PlayerStatueBuilderException {
        // Encode the username in the URL to prevent spaces or weird characters from breaking the URI
        String url = "https://api.mojang.com/users/profiles/minecraft/" + username.trim();
        HttpResponse<String> response = HTTPUtils.fetch(url);
        if (response == null) {
            throw new PlayerStatueBuilderException("Failed to get uuid response from Mojang");
        }

        if (response.statusCode() == 404 || response.statusCode() == 204) {
            // Mojang returns 404 or 204 if the username is not registered to an active account
            throw new IllegalArgumentException("No Minecraft account found with username: " + username);
        } else if (response.statusCode() == 429) {
            throw new PlayerStatueBuilderException("Mojang API Rate Limit exceeded. (Too many requests for this profile/IP)");
        } else if (response.statusCode() != 200) {
            throw new PlayerStatueBuilderException("Failed to fetch profile. HTTP status: " + response.statusCode());
        }

        String jsonResponse = response.body();

        // Extract the "id" field using regex
        Matcher matcher = ID_PATTERN.matcher(jsonResponse);
        if (matcher.find()) {
            return matcher.group(1); // Returns the clean, dashless 32-character UUID
        }

        throw new PlayerStatueBuilderException("Could not parse UUID from Mojang's response: " + jsonResponse);
    }
}
