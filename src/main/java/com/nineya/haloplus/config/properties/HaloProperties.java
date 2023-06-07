package com.nineya.haloplus.config.properties;

import static com.nineya.haloplus.model.support.HaloConst.FILE_SEPARATOR;
import static com.nineya.haloplus.model.support.HaloConst.TEMP_DIR;
import static com.nineya.haloplus.model.support.HaloConst.USER_HOME;
import static com.nineya.haloplus.utils.HaloUtils.ensureSuffix;

import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import com.nineya.haloplus.model.enums.Mode;


/**
 * Halo-Plus configuration properties.
 *
 * @author johnniang
 * @author ryanwang
 * @date 2019-03-15
 */
@Data
@ConfigurationProperties("halo")
public class HaloProperties {

    /**
     * Authentication enabled.
     */
    private boolean authEnabled = true;

    /**
     * Halo-Plus startup mode.
     */
    private Mode mode = Mode.PRODUCTION;

    /**
     * Admin path.
     */
    private String adminPath = "admin";

    /**
     * Work directory.
     */
    private String workDir = ensureSuffix(USER_HOME, FILE_SEPARATOR) + ".halo" + FILE_SEPARATOR;

    /**
     * Halo-Plus backup directory.(Not recommended to modify this config);
     */
    private String backupDir =
        ensureSuffix(TEMP_DIR, FILE_SEPARATOR) + "halo-backup" + FILE_SEPARATOR;

    /**
     * Halo-Plus backup markdown directory.(Not recommended to modify this config);
     */
    private String backupMarkdownDir =
        ensureSuffix(TEMP_DIR, FILE_SEPARATOR) + "halo-backup-markdown" + FILE_SEPARATOR;

    /**
     * Halo-Plus data export directory.
     */
    private String dataExportDir =
        ensureSuffix(TEMP_DIR, FILE_SEPARATOR) + "halo-data-export" + FILE_SEPARATOR;

    /**
     * Upload prefix.
     */
    private String uploadUrlPrefix = "upload";

    /**
     * Download Timeout.
     */
    private Duration downloadTimeout = Duration.ofSeconds(30);

    /**
     * cache store impl
     * memory
     * level
     */
    private String cache = "memory";
}
