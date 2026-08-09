package com.skytechbytes.playerstatuebuilder.lookup;

import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilderException;
import com.skytechbytes.playerstatuebuilder.builder.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class SkinFetchers {
}

class MineskinSkinFetcher implements SkinFetcher {

    @Override
    public BufferedImage getSkinImage(String name, String uuid) throws PlayerStatueBuilderException {
        String endpoint = "https://mineskin.eu/download/";
        try {
            // download based on username
            URL url = new URL(endpoint + name);
            return ImageIO.read(url);
        } catch (Exception e) {
            throw new PlayerStatueBuilderException("Could not read image from mineskin: " + e.getMessage());
        }
    }
}

//class CrafatarSkinFetcher implements SkinFetcher {
//
//    @Override
//    public BufferedImage getSkinImage(String name, String uuid) throws PlayerStatueBuilderException {
//        String endpoint = "https://crafatar.skyblock.net/skins/";
//        try {
//            // download based on uuid
//            URL url = new URL(endpoint + uuid);
//            return ImageIO.read(url);
//        } catch (Exception e) {
//            throw new PlayerStatueBuilderException("Could not read image from crafatar: " + e.getMessage());
//        }
//    }
//}

class MCHeadsSkinFetcher implements SkinFetcher {

    @Override
    public BufferedImage getSkinImage(String name, String uuid) throws PlayerStatueBuilderException {
        String endpoint = "https://mc-heads.net/download/";
        try {
            // download based on uuid
            URL url = new URL(endpoint + uuid);
            return ImageIO.read(url);
        } catch (Exception e) {
            throw new PlayerStatueBuilderException("Could not read image from mc-heads: " + e.getMessage());
        }
    }
}