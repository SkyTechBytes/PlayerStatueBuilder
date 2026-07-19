package com.skytechbytes.playerstatuebuilder.lookup;

import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilderException;

public interface UUIDFetcher {
    String getUUID(String username) throws PlayerStatueBuilderException;
}
