package com.nineya.haloplus.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.nineya.haloplus.exception.NotFoundException;
import com.nineya.haloplus.model.entity.SheetMeta;
import com.nineya.haloplus.repository.SheetMetaRepository;
import com.nineya.haloplus.repository.SheetRepository;
import com.nineya.haloplus.service.SheetMetaService;

/**
 * Sheet meta service implementation class.
 *
 * @author ryanwang
 * @author ikaisec
 * @date 2019-08-04
 */
@Slf4j
@Service
public class SheetMetaServiceImpl extends BaseMetaServiceImpl<SheetMeta>
    implements SheetMetaService {

    private final SheetMetaRepository sheetMetaRepository;

    private final SheetRepository sheetRepository;

    public SheetMetaServiceImpl(SheetMetaRepository sheetMetaRepository,
        SheetRepository sheetRepository) {
        super(sheetMetaRepository);
        this.sheetMetaRepository = sheetMetaRepository;
        this.sheetRepository = sheetRepository;
    }

    @Override
    public void validateTarget(@NonNull Integer sheetId) {
        sheetRepository.findById(sheetId)
            .orElseThrow(() -> new NotFoundException("查询不到该页面的信息").setErrorData(sheetId));
    }
}
