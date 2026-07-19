package com.skytechbytes.playerstatuebuilder.lookup;

import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilderException;

import java.awt.image.BufferedImage;

public interface SkinFetcher {
    BufferedImage getSkinImage(String name, String uuid) throws PlayerStatueBuilderException;
}
