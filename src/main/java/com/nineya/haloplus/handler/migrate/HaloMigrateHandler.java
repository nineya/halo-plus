package com.nineya.haloplus.handler.migrate;

import java.io.IOException;

import com.nineya.haloplus.service.BackupService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.nineya.haloplus.model.enums.MigrateType;

/**
 * @author ryanwang
 * @date 2020-03-14
 */
@Component
public class HaloMigrateHandler implements MigrateHandler {

    private final BackupService backupService;

    public HaloMigrateHandler(BackupService backupService) {
        this.backupService = backupService;
    }

    @Override
    public void migrate(MultipartFile file) {
        try {
            backupService.importData(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean supportType(MigrateType type) {
        return MigrateType.HALO.equals(type);
    }
}
