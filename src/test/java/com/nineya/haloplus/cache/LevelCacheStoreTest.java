package com.nineya.haloplus.cache;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import com.nineya.haloplus.config.properties.HaloProperties;
import com.nineya.haloplus.model.support.HaloConst;
import com.nineya.haloplus.utils.FileUtils;
import org.iq80.leveldb.DB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author guqing
 * @date 2022-03-02
 */
public class LevelCacheStoreTest {
    LevelCacheStore cacheStore;
    HaloProperties haloProperties = new HaloProperties();

    @BeforeEach
    void setUp() throws IOException {
        String testDir = FileUtils.createTempDirectory().toString();
        haloProperties.setWorkDir(testDir + HaloConst.FILE_SEPARATOR);
        cacheStore = new LevelCacheStore(haloProperties);
        cacheStore.init();
    }

    @Test
    public void corruptCacheStructureTest() {
        cacheStore.put("A", "B");

        // Simulate corrupt cache structure
        DB levelDb = (DB) ReflectionTestUtils.getField(cacheStore, "LEVEL_DB");
        levelDb.put("B".getBytes(StandardCharsets.UTF_8),
            "NOT_JSON".getBytes(StandardCharsets.UTF_8));

        Optional<CacheWrapper> bOpt = cacheStore.getAny("B", CacheWrapper.class);
        assertThat(bOpt).isNotNull();
        assertThat(bOpt.isEmpty()).isTrue();

        assertThat(cacheStore.toMap().toString()).isEqualTo("{A=B, B=null}");
    }

    @AfterEach
    public void cleanUp() {
        cacheStore.delete("A");
        cacheStore.delete("B");
    }
}
