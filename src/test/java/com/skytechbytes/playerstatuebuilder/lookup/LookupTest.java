package com.skytechbytes.playerstatuebuilder.lookup;

import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilderException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.image.BufferedImage;

public class LookupTest {
    private UUIDFetcher[] prevUUIDFetchers;
    private SkinFetcher[] prevSkinFetcher;

    @BeforeEach
    void setUp() {
        LookupUtils.clearSkinCache();
        prevUUIDFetchers = LookupUtils.uuidFetchers;
        prevSkinFetcher = LookupUtils.skinFetchers;
    }

    @AfterEach
    void tearDown() {
        LookupUtils.clearSkinCache();
        LookupUtils.uuidFetchers = prevUUIDFetchers;
        LookupUtils.skinFetchers = prevSkinFetcher;
    }

    @Test
    void shouldFetchSkin() throws Exception {
        BufferedImage response = LookupUtils.getCloudSkinImage("SkyTechBytes");
        assertNotNull(response);
        assertEquals(64, response.getWidth());
    }

    @Test
    void shouldFallbackWhenDegraded() throws Exception {
        FakeUUIDFetcher fakeUUIDFetcher = new FakeUUIDFetcher(new PlayerStatueBuilderException("fake uuid exception"), null);
        FakeSkinFetcher fakeSkinFetcher = new FakeSkinFetcher(new PlayerStatueBuilderException("fake skin fetch exception"));
        LookupUtils.uuidFetchers = new UUIDFetcher[]{ fakeUUIDFetcher, new PlayerDBUUIDFetcher() };
        LookupUtils.skinFetchers = new SkinFetcher[]{ fakeSkinFetcher, new MineskinSkinFetcher() };

        BufferedImage response = LookupUtils.getCloudSkinImage("SkyTechBytes");
        assertNotNull(response);
        assertEquals(64, response.getWidth());

        assertEquals(1, fakeUUIDFetcher.failures);
        assertEquals(1, fakeSkinFetcher.failures);
    }

    @Test
    void shouldFailImmediatelyIfIllegalArgument() throws Exception {
        FakeUUIDFetcher fakeUUIDFetcher = new FakeUUIDFetcher(new PlayerStatueBuilderException("fake uuid exception"), null);
        LookupUtils.uuidFetchers = new UUIDFetcher[]{ new MojangUUIDFetcher(), fakeUUIDFetcher};
        PlayerStatueBuilderException e = assertThrows(
                PlayerStatueBuilderException.class,
                () -> LookupUtils.getCloudSkinImage("a")
        );
        assertEquals(LookupUtils.uuidNotFoundMsg, e.getMessage());

        assertEquals(0, fakeUUIDFetcher.failures); // second doesn't even run
    }

    @Test
    void shouldFetchSkinAlt() throws Exception {
        // might as well test alt uuid getter
        LookupUtils.uuidFetchers = new UUIDFetcher[]{ new PlayerDBUUIDFetcher() };

        LookupUtils.skinFetchers = new SkinFetcher[]{ new MCHeadsSkinFetcher() };
        BufferedImage response = LookupUtils.getCloudSkinImage("SkyTechBytes");
        assertNotNull(response);
        assertEquals(64, response.getWidth());

        LookupUtils.skinFetchers = new SkinFetcher[]{ new MineskinSkinFetcher() };
        response = LookupUtils.getCloudSkinImage("SkyTechBytes");
        assertNotNull(response);
        assertEquals(64, response.getWidth());
    }

    @Test
    void shouldCache() throws Exception {
        BufferedImage response = LookupUtils.getSkinImage("SkyTechBytes");
        assertNotNull(response);
        assertEquals(64, response.getWidth());

        FakeUUIDFetcher fakeUUIDFetcher = new FakeUUIDFetcher(new PlayerStatueBuilderException("uuid fetcher should not be accessed"), new IllegalArgumentException("should not be accessed"));
        FakeSkinFetcher fakeSkinFetcher = new FakeSkinFetcher(new PlayerStatueBuilderException("skin fetcher should not be accessed"));
        LookupUtils.uuidFetchers = new UUIDFetcher[]{ fakeUUIDFetcher };
        LookupUtils.skinFetchers = new SkinFetcher[]{ fakeSkinFetcher };


        // check if cached (should not access fetchers at all)
        response = LookupUtils.getSkinImage("SkyTechBytes");
        assertNotNull(response);
        assertEquals(64, response.getWidth());

        assertEquals(0, fakeUUIDFetcher.failures);
        assertEquals(0, fakeSkinFetcher.failures);

        // does not hit cache (should access fetcher and fail)
        fakeUUIDFetcher = new FakeUUIDFetcher(new PlayerStatueBuilderException("flag hit"), new IllegalArgumentException("flag hit"));
        LookupUtils.uuidFetchers = new UUIDFetcher[]{ fakeUUIDFetcher };
        assertThrows(Exception.class, () -> LookupUtils.getSkinImage("Technoblade"));
        assertEquals(1, fakeUUIDFetcher.failures);


        LookupUtils.clearSkinCache();
        // now it should attempt to fetch uuid again
        fakeUUIDFetcher = new FakeUUIDFetcher(null, new IllegalArgumentException("flag hit"));
        LookupUtils.uuidFetchers = new UUIDFetcher[]{ fakeUUIDFetcher };
        assertThrows(Exception.class, () -> LookupUtils.getSkinImage("SkyTechBytes"));
        assertEquals(1, fakeUUIDFetcher.failures); // it accessed network
    }
}
