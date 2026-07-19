package com.skytechbytes.playerstatuebuilder.lookup;

import com.skytechbytes.playerstatuebuilder.Log;
import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilderException;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MojangSkinFetcher implements SkinFetcher {
    // Regex to locate the "textures" Base64 block within the outer profile JSON
    private static final Pattern BASE64_TEXTURE_PATTERN = Pattern.compile(
            "\\{\\s*\"name\"\\s*:\\s*\"textures\"\\s*,\\s*\"value\"\\s*:\\s*\"([^\"]+)\""
    );
    private static final Pattern BASE64_TEXTURE_REVERSED_PATTERN = Pattern.compile(
            "\\{\\s*\"value\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"name\"\\s*:\\s*\"textures\""
    );
    // Regex to locate the actual raw skin URL inside the decoded nested JSON (finds: "SKIN":{"url":"...")
    private static final Pattern SKIN_URL_PATTERN = Pattern.compile(
            "\"SKIN\"\\s*:\\s*\\{\\s*\"url\"\\s*:\\s*\"([^\"]+)\""
    );

    @Override
    public BufferedImage getSkinImage(String name, String uuid) throws PlayerStatueBuilderException {
        String url = "https://sessionserver.mojang.com/session/minecraft/profile/" + uuid;
        HttpResponse<String> response = HTTPUtils.fetch(url);
        if (response == null) {
            throw new PlayerStatueBuilderException("Failed to get skin response from Mojang");
        }

        if (response.statusCode() == 204) { // HTTP 204 means No Content (UUID doesn't exist)
            throw new IllegalArgumentException("No Minecraft profile found for UUID: " + uuid);
        } else if (response.statusCode() == 429) {
            throw new PlayerStatueBuilderException("Mojang API Rate Limit exceeded. (Too many requests for this profile/IP)");
        } else if (response.statusCode() != 200) {
            throw new PlayerStatueBuilderException("Failed to fetch profile. HTTP status: " + response.statusCode());
        }

        String jsonResponse = response.body().replaceAll("\\s+", ""); // Strip whitespace for regex matching
        // Log.log("Debug response: " + jsonResponse);

        // Step 1: Extract the Base64 textures payload from the outer JSON
        String base64Payload = getBase64Payload(uuid, jsonResponse);

        // Step 2: Decode the Base64 payload
        byte[] decodedBytes = Base64.getDecoder().decode(base64Payload);
        String decodedTexturesJson = new String(decodedBytes, StandardCharsets.UTF_8).replaceAll("\\s+", "");

        // Step 3: Extract the direct image URL of the skin from the nested JSON
        Matcher skinUrlMatcher = SKIN_URL_PATTERN.matcher(decodedTexturesJson);
        if (!skinUrlMatcher.find()) {
            throw new IllegalArgumentException("Decoded profile data has no active 'SKIN' texture.");
        }
        String skinUrl = skinUrlMatcher.group(1);

        // Step 4: Download and parse the raw PNG texture directly into a BufferedImage
        try {
            return ImageIO.read(URI.create(skinUrl).toURL());
        } catch (Exception e) {
            throw new PlayerStatueBuilderException("Failed to read the skin image from the Mojang CDN (" + skinUrl + ")");
        }
    }

    private static @NotNull String getBase64Payload(String uuid, String jsonResponse) throws PlayerStatueBuilderException {
        String base64Payload = null;
        Matcher matcher = BASE64_TEXTURE_PATTERN.matcher(jsonResponse);
        if (matcher.find()) {
            base64Payload = matcher.group(1);
        } else {
            Matcher reversedMatcher = BASE64_TEXTURE_REVERSED_PATTERN.matcher(jsonResponse);
            if (reversedMatcher.find()) {
                base64Payload = reversedMatcher.group(1);
            }
        }

        if (base64Payload == null) {
            throw new PlayerStatueBuilderException("The profile for " + uuid + " does not contain any texture properties.");
        }
        return base64Payload;
    }
}
