package com.skytechbytes.playerstatuebuilder.lookup;

import com.skytechbytes.playerstatuebuilder.Log;
import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilder;
import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilderException;
import com.skytechbytes.playerstatuebuilder.builder.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 *
 * @author SkyTechBytes
 * Big thanks to Crafatar and Playerdb.co for an amazing api to use for names and skins
 *
 */
public class LookupUtils {
    private static final Map<String, BufferedImage> cache = new ConcurrentHashMap<>();
    private static final UUIDFetcher[] uuidFetchers = new UUIDFetcher[]{ new MojangUUIDFetcher(), new PlayerDBUUIDFetcher() };
    private static final SkinFetcher[] skinFetchers = new SkinFetcher[]{ new MojangSkinFetcher(), new CrafatarSkinFetcher(), new MCHeadsSkinFetcher(), new MineskinSkinFetcher()};
    private static final String uuidNotFoundMsg = "Failed to lookup uuid, likely because player specified does not exist.";

    public static BufferedImage getSkinImage(String name) throws Exception {
        BufferedImage bi = null;
        if (!cache.containsKey(name)) {
            if (isDiskSkin(name)) {
                bi = getDiskSkinImage(name);
            } else {
                bi = getCloudSkinImage(name);
            }
            cache.put(name, bi);
        } else {
            bi = cache.get(name);
        }
        return bi;
    }
    public static boolean isDiskSkin(String name) {
        return name.startsWith(".") && name.length() > 1;
    }
    public static BufferedImage getDiskSkinImage(String name) throws Exception {
        BufferedImage bi = null;
        /*
         * The skin is on the local disk in the plugin's data folder.
         */
        // Remove the weirdness at the front
        String fileName = name.substring(1);

        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        boolean hasSpecialChar = pattern.matcher(fileName).find();

        if (hasSpecialChar) {
            throw new PlayerStatueBuilderException("Admins: make sure your skin image is alphanumeric only; Your skin name can't have a special character (don't include .png).");
        }

        if (!(fileName.endsWith(".png") || fileName.endsWith(".PNG"))) {
            fileName = fileName + ".png";
        }

        try {

            Path customFile = Paths.get(PlayerStatueBuilder.instance.getDataFolder().toURI().resolve(fileName));

            bi = ImageIO.read(customFile.toFile());

            // convert to argb
            bi = convertToARGB(bi);

            if (bi == null) {
                throw new PlayerStatueBuilderException("");
            }
        } catch (Exception e) {
            throw new PlayerStatueBuilderException("Unable to load file from plugin data folder. Make sure the name is spelled correctly and the skin"
                    + " image file is in the same folder as the config file.");
        }
        return ImageUtil.deepCopy(bi);
    }
    private static BufferedImage convertToARGB(BufferedImage originalImage) {
        // Create a new buffered image with the ARGB color model
        BufferedImage argbImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        // Draw the original image onto the new ARGB buffered image
        Graphics2D g2d = argbImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, null);
        g2d.dispose();

        return argbImage;
    }
    public static BufferedImage getCloudSkinImage(String name) throws Exception {
        /*
         * If the skin has no prefix, it is a normal skin that must be retrieved from the cloud.
         */

        // get player UUID using available methods
        String uuid = null;
        for (UUIDFetcher fetcher : uuidFetchers) {
            try {
                uuid = fetcher.getUUID(name);
                if (uuid != null) {
                    break;
                }
            } catch (IllegalArgumentException iae) {
                throw new PlayerStatueBuilderException(uuidNotFoundMsg);
            } catch (Exception e) {
                Log.log("Unable to lookup uuid: " + e.getMessage());
            }
        }
        if (uuid == null) {
            throw new PlayerStatueBuilderException(uuidNotFoundMsg);
        }

        // get player skin using available methods
        for (SkinFetcher fetcher : skinFetchers) {
            try {
                BufferedImage bi = fetcher.getSkinImage(name, uuid);
                if (bi != null) {
                    return bi;
                }
            } catch (Exception e) {
                Log.log("Could not fetch skin: " + e.getMessage());
            }
        }

        throw new PlayerStatueBuilderException("Could not obtain skin from the API or backup API. Please try again later.");
    }

    public static void clearSkinCache() {
        Log.log("Cleared skin cache!");
        cache.clear();
    }
}
