package com.nineya.haloplus.service;

import com.nineya.haloplus.service.base.CrudService;
import org.springframework.data.domain.Page;
import com.nineya.haloplus.model.dto.LogDTO;
import com.nineya.haloplus.model.entity.Log;

/**
 * Log service interface.
 *
 * @author johnniang
 * @date 2019-03-14
 */
public interface LogService extends CrudService<Log, Long> {

    /**
     * Lists latest logs.
     *
     * @param top top number must not be less than 0
     * @return a page of latest logs
     */
    Page<LogDTO> pageLatest(int top);
}
