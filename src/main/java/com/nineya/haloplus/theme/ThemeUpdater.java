package com.nineya.haloplus.theme;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.nineya.haloplus.handler.theme.config.support.ThemeProperty;
import com.nineya.haloplus.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Theme updater.
 *
 * @author johnniang
 */
public interface ThemeUpdater {

    Logger log = LoggerFactory.getLogger(ThemeUpdater.class);

    /**
     * Update theme property.
     *
     * @param themeId theme id
     * @return updated theme property
     */
    ThemeProperty update(String themeId) throws IOException;

    /**
     * Backup old theme.
     *
     * @param themeProperty theme property
     * @return theme backup path
     * @throws IOException throws io exception
     */
    static Path backup(final ThemeProperty themeProperty) throws IOException {
        final var themePath = Paths.get(themeProperty.getThemePath());
        Path tempDirectory = null;
        try {
            tempDirectory = FileUtils.createTempDirectory();
            FileUtils.copyFolder(themePath, tempDirectory);
            log.info("Backup theme: {} to {} successfully!", themeProperty.getId(), tempDirectory);
            return tempDirectory;
        } catch (IOException e) {
            // clear temp directory
            FileUtils.deleteFolderQuietly(tempDirectory);
            throw e;
        }
    }

    static void restore(final Path backupPath, final ThemeProperty oldThemeProperty)
        throws IOException {
        final var targetPath = Paths.get(oldThemeProperty.getThemePath());
        log.info("Restoring backup path: {} to target path: {}", backupPath, targetPath);
        // copy backup to target path
        FileUtils.copyFolder(backupPath, targetPath);
        log.debug("Copied backup path: {} to target path: {} successfully!", backupPath,
            targetPath);
        // delete backup
        FileUtils.deleteFolderQuietly(backupPath);
        log.debug("Deleted backup path: {} successfully!", backupPath);
        log.info("Restored backup path: {} to target path: {} successfully!", backupPath,
            targetPath);
    }
}
