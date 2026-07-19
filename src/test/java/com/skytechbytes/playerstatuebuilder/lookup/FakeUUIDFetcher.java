package com.skytechbytes.playerstatuebuilder.lookup;

import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilderException;

public class FakeUUIDFetcher implements UUIDFetcher {
    private PlayerStatueBuilderException e;
    private RuntimeException re;
    int failures = 0;
    public FakeUUIDFetcher(PlayerStatueBuilderException e, RuntimeException re) {
        this.e = e;
        this.re = re;
    }

    @Override
    public String getUUID(String username) throws PlayerStatueBuilderException {
        if (e != null) {
            failures += 1;
            throw e;
        }
        if (re != null) {
            failures += 1;
            throw re;
        }
        return "280c3309-f324-4169-b9e0-7e88f965d68c";
    }
}
