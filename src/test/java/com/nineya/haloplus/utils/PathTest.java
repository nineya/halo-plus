package com.nineya.haloplus.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URI;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Path test.
 *
 * @author johnniang
 * @date 19-5-20
 */
@Slf4j
class PathTest {

    @Test
    void getPathOfJarFileFailure() {
        String file = "jar:file:/path/to/jar/xxx.jar!/BOOT-INF/classes!/templates/themes";
        assertThrows(FileSystemNotFoundException.class, () -> {
            URI uri = new URI(file);
            Path path = Paths.get(uri);
            log.debug("Path: " + path.toString());
        });
    }
}
