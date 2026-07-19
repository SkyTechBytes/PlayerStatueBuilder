package com.skytechbytes.playerstatuebuilder.lookup;

import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilderException;

import java.awt.image.BufferedImage;

public class FakeSkinFetcher implements SkinFetcher {
    private PlayerStatueBuilderException e;
    int failures = 0;
    public FakeSkinFetcher(PlayerStatueBuilderException e) {
        this.e = e;
    }

    @Override
    public BufferedImage getSkinImage(String name, String uuid) throws PlayerStatueBuilderException {
        if (e != null) {
            failures += 1;
            throw e;
        }
        return null;
    }
}
